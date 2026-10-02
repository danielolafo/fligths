-- 1. GEOGRAPHY & INFRASTRUCTURE
CREATE TABLE airports (
    airport_code VARCHAR(3) PRIMARY KEY, -- e.g., 'LAX', 'JFK'
    airport_name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    timezone VARCHAR(50) NOT NULL
);

-- 2. AIRLINES
CREATE TABLE airlines (
    airline_code VARCHAR(2) PRIMARY KEY, -- e.g., 'AA', 'DL'
    airline_name VARCHAR(100) NOT NULL,
    country_of_origin VARCHAR(100)
);

-- 3. FLIGHT SCHEDULES (The Blueprint)
CREATE TABLE flight_schedules (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    airline_code VARCHAR(2),
    flight_number VARCHAR(10) NOT NULL,
    departure_airport VARCHAR(3),
    arrival_airport VARCHAR(3),
    scheduled_departure_time TIME NOT NULL,
    scheduled_arrival_time TIME NOT NULL,
    days_of_week VARCHAR(7), -- e.g., '1234567' for daily, '135' for Mon/Wed/Fri
    FOREIGN KEY (airline_code) REFERENCES airlines(airline_code),
    FOREIGN KEY (departure_airport) REFERENCES airports(airport_code),
    FOREIGN KEY (arrival_airport) REFERENCES airports(airport_code)
);

-- 4. SPECIFIC FLIGHT INSTANCES (Actual Inventory)
CREATE TABLE flights (
    flight_id INT PRIMARY KEY AUTO_INCREMENT,
    schedule_id INT,
    departure_date DATE NOT NULL,
    arrival_date DATE NOT NULL,
    actual_departure_time TIMESTAMP,
    actual_arrival_time TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Scheduled', -- Scheduled, Delayed, Cancelled
    FOREIGN KEY (schedule_id) REFERENCES flight_schedules(schedule_id)
);

-- 5. PRICING, CABINS, AND OFFERS (The "Kayak" Deal Aggregator Part)
CREATE TABLE flight_offers (
    offer_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_id INT,
    provider_name VARCHAR(50) NOT NULL, -- e.g., 'Expedia', 'Direct Airline', 'Booking.com'
    cabin_class VARCHAR(20) NOT NULL, -- Economy, Premium Economy, Business, First
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(3) DEFAULT 'USD',
    seats_remaining INT NOT NULL,
    is_deal BOOLEAN DEFAULT FALSE,
    discount_percentage DECIMAL(5, 2) DEFAULT 0.00,
    valid_until TIMESTAMP,
    booking_url TEXT,
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);


CREATE TABLE flights_prices (
  id INT PRIMARY KEY AUTO_INCREMENT,
  origin VARCHAR(255),
  destination VARCHAR(255),
  month_num VARCHAR(20),
  day_of_month INT,
  price DECIMAL(10, 2),
  airline_code VARCHAR(2), -- e.g., 'AF' -> airlines.airline_code
  FOREIGN KEY (airline_code) REFERENCES airlines(airline_code)
);