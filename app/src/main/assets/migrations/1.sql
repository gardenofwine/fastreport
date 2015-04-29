CREATE TABLE streets (
    _id INTEGER PRIMARY KEY AUTOINCREMENT,
    street_name TEXT NOT NULL,
    search_street_name TEXT NOT NULL,
    english_street_name TEXT,
    street_code INTEGER NOT NULL UNIQUE,
    first_apartment_number INTEGER,
    last_apartment_number INTEGER
);