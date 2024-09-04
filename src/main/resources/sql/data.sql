insert into item_sale_info(member_id, inventory_id, item_id, member_name, item_name, item_price, item_type, description, status, quantity)
values (1, 1, 1, '유저1', '강철검', 1000, 'SWORD', '일반 강철검', 'SELLING', 1),
       (2, 2, 2, '유저2', '목검', 500, 'SWORD', '일반 목검', 'SOLD_OUT', 2),
       (3, 3, 3, '유저3', '다이아검', 2000, 'SWORD', '일반 다이아검', 'WAIT', 3),
       (3, 4, 4, '유저3', '강철도끼', 1500, 'AXE', '일단 강철도끼', 'SELLING', 3);