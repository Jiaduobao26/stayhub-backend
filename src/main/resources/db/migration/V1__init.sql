CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(50)  NOT NULL
);

CREATE TABLE listings (
    id           BIGSERIAL PRIMARY KEY,
    host_id      BIGINT       NOT NULL,
    name         VARCHAR(255) NOT NULL,
    address      VARCHAR(255) NOT NULL,
    description  TEXT,
    guest_number INTEGER      NOT NULL,
    image_urls   TEXT[],
    location     GEOMETRY(POINT),
    CONSTRAINT fk_listing_host FOREIGN KEY (host_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE bookings (
    id              BIGSERIAL PRIMARY KEY,
    guest_id        BIGINT NOT NULL,
    listing_id      BIGINT NOT NULL,
    check_in_date   DATE   NOT NULL,
    check_out_date  DATE   NOT NULL,
    CONSTRAINT fk_booking_listing FOREIGN KEY (listing_id) REFERENCES listings (id) ON DELETE CASCADE,
    CONSTRAINT fk_booking_guest   FOREIGN KEY (guest_id)   REFERENCES users (id)    ON DELETE CASCADE
);

-- Spatial index for location-based search
CREATE INDEX idx_listings_location ON listings USING GIST (location);

-- Indexes on foreign keys
CREATE INDEX idx_listings_host_id   ON listings (host_id);
CREATE INDEX idx_bookings_guest_id  ON bookings (guest_id);
CREATE INDEX idx_bookings_listing_id ON bookings (listing_id);

-- Index for date range queries
CREATE INDEX idx_bookings_dates ON bookings (listing_id, check_in_date, check_out_date);
