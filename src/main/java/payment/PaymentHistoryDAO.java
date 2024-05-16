package payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PaymentHistoryDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/iotbay?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Hailey5149!";

    public List<PaymentHistory> searchPayments(LocalDate orderDate, String paymentId) {
        List<PaymentHistory> payments = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM payments WHERE date = ?");
        
        List<Object> parameters = new ArrayList<>();
        parameters.add(Timestamp.valueOf(orderDate.atStartOfDay()));

        if (paymentId != null && !paymentId.isEmpty()) {
            query.append(" AND id = ?");
            parameters.add(Integer.parseInt(paymentId));
        }

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query.toString());) {
            
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PaymentHistory paymentHistory = new PaymentHistory();
                paymentHistory.setId(rs.getInt("id"));
                paymentHistory.setDateTime(rs.getTimestamp("date").toLocalDateTime());
                paymentHistory.setAmount(rs.getDouble("amount"));
                paymentHistory.setPayment(rs.getString("customer"));             
                paymentHistory.setStatus(rs.getString("status"));
                payments.add(paymentHistory);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return payments;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    t = t.getCause();
                }
            }
        }
    }
}
