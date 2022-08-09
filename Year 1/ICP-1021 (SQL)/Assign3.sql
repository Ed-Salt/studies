#4a
select distinct fName,
sName
from Customers c, Orders o
where c.customerID not in (select customerID from Orders);

#4b
select orderID,
sum(price * quantity) as 'amount_spent'
from Purchases u, Products o
where o.productID = u.productID
group by orderID;

#5a
update Products
set price = (price*1.1)
where categoryID = 1;

#5b
insert Orders
value (7, 2, "2016-11-14");
insert Purchases
value (7, 1, 1, "Not yet shipped");

#6a
create view product_prices(productName, price)
as select productName,
price
from Products;

#6b
create view products_purchased(orders_id, 
orders_purchase_date, products_name, purchases_quantity)
as (select o.orderID,
purchaseDate,
productName,
quantity
from Orders o, Purchases u, Products p
where o.orderID = u.orderID
and u.productID = p.productIDproducts_purchasedorders_id
);

#7a
select productName
from product_prices
where price > 6;

#7b
select products_name,
sum(purchases_quantity) as 'total_purchased'
from products_purchased
where orders_purchase_date > "2016-03-01"
group by products_name;

#8a
update product_prices
set price = (price*1.1)
where price < 10
and productName != "Donation";

#8b
delete from products_purchased
where orders_id = 1;
#It can't remove details from the view, as they 
#are just being read from the table