<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>IoTBay | Checkout - Member</title>

    <!-- Include the Google Font (Poppins) -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap">

    <!-- Iconscout CDN -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">

    <!-- css.gg CDN -->
    <link href='https://unpkg.com/css.gg@2.0.0/icons/css/menu.css' rel='stylesheet'>

    <!-- SWIPER JS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>

    <link rel="icon" type="image/x-icon" href="assets/iot-bay.ico"/>
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
    
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
    
    <link rel="stylesheet" href="css/styleCheckout.css" />
</head>
<body>
    <!--================= NAVBAR ======================-->
    <nav>
        <div class="container nav__container">
            <h3><a href="landing.jsp">IoTBay</a></h3>
            <ul class="nav__menu">
                <li><a href="landing.jsp">Home</a></li>
                <li><a href="landing.jsp">About</a></li>
                <li><a href="logout">Logout</a></li>      	
	            <% if (session.getAttribute("name") != null) { %>
					<li><a href="account.jsp"><%= session.getAttribute("name") %></a></li>
	            <% } else { %>
	                <li><small>Guest</small></li>
	            <% } %>               	
            </ul>
            <button id="open-menu-btn"><i class="uil uil-bars"></i></button>
            <button id="close-menu-btn"><i class="uil uil-times"></i></button>
        </div>
    </nav>
    <!--================= END NAVBAR ======================-->

    <!--================= HEADER ======================-->
<header>
  <!-- Heading -->
  <div class="bg-primary">
    <div class="container py-4">
      <!-- Breadcrumb -->
      <div class="d-flex">
        <h6 class="mb-0">
          <a href="" class="text-white-50">Home</a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white-50">2. Shopping cart</a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white"><u>3. Order info</u></a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white-50">4. Payment</a>
        </h6>
      </div>
      <!-- Breadcrumb -->
    </div>
  </div>
  <!-- Heading -->
</header>

<section class="bg-light py-5">
  <div class="container">
    <div class="row">
      <div class="col-xl-8 col-lg-8 mb-4">
        <!-- Checkout -->
        <div class="card shadow-0 border">
          <div class="p-4">
            <h5 class="card-title mb-3">Checkout</h5>
            <div class="row">
              <div class="col-6 mb-3">
                <p class="mb-0">First name</p>
                <div class="form-outline">
                  <input type="text" id="typeText" placeholder="Type here" class="form-control" />
                </div>
              </div>

              <div class="col-6">
                <p class="mb-0">Last name</p>
                <div class="form-outline">
                  <input type="text" id="typeText" placeholder="Type here" class="form-control" />
                </div>
              </div>

              <div class="col-6 mb-3">
                <p class="mb-0">Phone</p>
                <div class="form-outline">
                  <input type="tel" id="typePhone" value="+48 " class="form-control" />
                </div>
              </div>

              <div class="col-6 mb-3">
                <p class="mb-0">Email</p>
                <div class="form-outline">
                  <input type="email" id="typeEmail" placeholder="example@gmail.com" class="form-control" />
                </div>
              </div>
            </div>

            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
              <label class="form-check-label" for="flexCheckDefault">Keep me up to date on news</label>
            </div>

            <hr class="my-4" />

            <h5 class="card-title mb-3">Shipping info</h5>

            <div class="row mb-3">
              <div class="col-lg-4 mb-3">
                <!-- Default checked radio -->
                <div class="form-check h-100 border rounded-3">
                  <div class="p-3">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" checked />
                    <label class="form-check-label" for="flexRadioDefault1">
                      Express delivery <br />
                      <small class="text-muted">3-4 days via Fedex </small>
                    </label>
                  </div>
                </div>
              </div>
              <div class="col-lg-4 mb-3">
                <!-- Default radio -->
                <div class="form-check h-100 border rounded-3">
                  <div class="p-3">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" />
                    <label class="form-check-label" for="flexRadioDefault2">
                      Post office <br />
                      <small class="text-muted">20-30 days via post </small>
                    </label>
                  </div>
                </div>
              </div>
              <div class="col-lg-4 mb-3">
                <!-- Default radio -->
                <div class="form-check h-100 border rounded-3">
                  <div class="p-3">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3" />
                    <label class="form-check-label" for="flexRadioDefault3">
                      Self pick-up <br />
                      <small class="text-muted">Come to our shop </small>
                    </label>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-sm-8 mb-3">
                <p class="mb-0">Address</p>
                <div class="form-outline">
                  <input type="text" id="typeText" placeholder="Type here" class="form-control" />
                </div>
              </div>

              <div class="col-sm-4 mb-3">
                <p class="mb-0">City</p>
                <div class="form-outline">
                  <input type="text" id="typeText" placeholder="Type here" class="form-control" />
                </div>
              </div>

              <div class="col-sm-4 mb-3">
                <p class="mb-0">House</p>
                <div class="form-outline">
                  <input type="text" id="typeText" placeholder="Type here" class="form-control" />
                </div>
              </div>

              <div class="col-sm-4 col-6 mb-3">
                <p class="mb-0">Postal code</p>
                <div class="form-outline">
                  <input type="text" id="typeText" class="form-control" />
                </div>
              </div>

              <div class="col-sm-4 col-6 mb-3">
                <p class="mb-0">Zip</p>
                <div class="form-outline">
                  <input type="text" id="typeText" class="form-control" />
                </div>
              </div>
            </div>

            <div class="form-check mb-3">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault1" />
              <label class="form-check-label" for="flexCheckDefault1">Save this address</label>
            </div>

            <div class="mb-3">
              <p class="mb-0">Message to seller</p>
              <div class="form-outline">
                <textarea class="form-control" id="textAreaExample1" rows="2"></textarea>
              </div>
            </div>

            <div class="float-end">
              <button class="btn btn-light border">Cancel</button>
              <button class="btn btn-success shadow-0 border">Continue</button>
            </div>
          </div>
        </div>
        <!-- Checkout -->
      </div>
      <div class="col-xl-4 col-lg-4 d-flex justify-content-center justify-content-lg-end">
        <div class="ms-lg-4 mt-4 mt-lg-0" style="max-width: 320px;">
          <h6 class="mb-3">Summary</h6>
          <div class="d-flex justify-content-between">
            <p class="mb-2">Total price:</p>
            <p class="mb-2">$195.90</p>
          </div>
          <div class="d-flex justify-content-between">
            <p class="mb-2">Discount:</p>
            <p class="mb-2 text-danger">- $60.00</p>
          </div>
          <div class="d-flex justify-content-between">
            <p class="mb-2">Shipping cost:</p>
            <p class="mb-2">+ $14.00</p>
          </div>
          <hr />
          <div class="d-flex justify-content-between">
            <p class="mb-2">Total price:</p>
            <p class="mb-2 fw-bold">$149.90</p>
          </div>

          <div class="input-group mt-3 mb-4">
            <input type="text" class="form-control border" name="" placeholder="Promo code" />
            <button class="btn btn-light text-primary border">Apply</button>
          </div>

          <hr />
          <h6 class="text-dark my-4">Items in cart</h6>

          <div class="d-flex align-items-center mb-4">
            <div class="me-3 position-relative">
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary">
                1
              </span>
              <img src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/7.webp" style="height: 96px; width: 96x;" class="img-sm rounded border" />
            </div>
            <div class="">
              <a href="#" class="nav-link">
                Gaming Headset with Mic <br />
                Darkblue color
              </a>
              <div class="price text-muted">Total: $295.99</div>
            </div>
          </div>

          <div class="d-flex align-items-center mb-4">
            <div class="me-3 position-relative">
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary">
                1
              </span>
              <img src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/5.webp" style="height: 96px; width: 96x;" class="img-sm rounded border" />
            </div>
            <div class="">
              <a href="#" class="nav-link">
                Apple Watch Series 4 Space <br />
                Large size
              </a>
              <div class="price text-muted">Total: $217.99</div>
            </div>
          </div>

          <div class="d-flex align-items-center mb-4">
            <div class="me-3 position-relative">
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary">
                3
              </span>
              <img src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/1.webp" style="height: 96px; width: 96x;" class="img-sm rounded border" />
            </div>
            <div class="">
              <a href="#" class="nav-link">GoPro HERO6 4K Action Camera - Black</a>
              <div class="price text-muted">Total: $910.00</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
    <!-- MDB -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
    <!-- Custom scripts -->
    <script type="text/javascript" src="js/script.js"></script>
</body>
</html>