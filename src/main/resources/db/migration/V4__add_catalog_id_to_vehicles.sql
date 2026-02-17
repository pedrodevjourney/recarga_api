ALTER TABLE vehicles ADD COLUMN catalog_id UUID NULL REFERENCES ev_catalog(id);

CREATE INDEX idx_vehicles_catalog_id ON vehicles (catalog_id);
