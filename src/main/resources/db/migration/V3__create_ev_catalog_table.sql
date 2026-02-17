CREATE TABLE ev_catalog (
    id UUID PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    variant VARCHAR(255) NULL,
    "year" INTEGER NOT NULL,
    battery_capacity_kwh DOUBLE PRECISION NULL,
    range_wltp_km DOUBLE PRECISION NULL,
    range_wltp_miles DOUBLE PRECISION NULL
);

CREATE INDEX idx_ev_catalog_brand ON ev_catalog (brand);
CREATE INDEX idx_ev_catalog_year ON ev_catalog ("year");
CREATE INDEX idx_ev_catalog_model ON ev_catalog (model);
