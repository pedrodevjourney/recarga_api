CREATE TABLE vehicles (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    nickname VARCHAR(255) NOT NULL,
    "year" INTEGER NOT NULL,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    connector_type VARCHAR(50) NOT NULL,
    battery_capacity_kwh DOUBLE PRECISION NOT NULL,
    plate VARCHAR(20) NULL
);

CREATE INDEX idx_vehicles_user_id ON vehicles (user_id);
