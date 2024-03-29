CREATE TABLE IF NOT EXISTS postgres.public.app_user (
                                                        id SERIAL PRIMARY KEY,
                                                        age INT NOT NULL,
                                                        first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
    );
CREATE TABLE IF NOT EXISTS postgres.public.purchase_info (
                                                             id SERIAL PRIMARY KEY,
                                                             amount DECIMAL(10, 2) NOT NULL,
    count INT NOT NULL,
    purchase_date DATE NOT NULL,
    purchase_item VARCHAR(255) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES postgres.public.app_user(id)
    );
