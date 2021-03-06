CREATE DATABASE myapp
GO
USE myapp
CREATE TABLE category
(
category_id INT NOT NULL,
category_name NVARCHAR(50)
CONSTRAINT PK_category_id PRIMARY KEY (category_id)
)
GO
CREATE TABLE product
(
product_id NVARCHAR(50) NOT NULL,
category_id INT,
product_name NVARCHAR(50),
price_import FLOAT,
price_export FLOAT,
amount INT,
CONSTRAINT PK_product_id PRIMARY KEY (product_id),
CONSTRAINT FK_product_category FOREIGN KEY(category_id) REFERENCES category(category_id),
)
GO
SELECT * FROM dbo.product
SELECT * FROM dbo.category
DELETE FROM category
INSERT INTO dbo.category
        ( category_id, category_name )
VALUES  ( 1, -- category_id - int
          N'Iphone'  -- category_name - nvarchar(50)
          )