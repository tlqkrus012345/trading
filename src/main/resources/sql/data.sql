insert into item_sale_info(member_id, inventory_id, item_id, member_name, item_name, item_price, item_type, description, status, quantity)
values (1, 1, 1, '유저1', '강철검', 1000, 'SWORD', '일반 강철검', 'SELLING', 1),
       (2, 2, 2, '유저2', '목검', 500, 'SWORD', '일반 목검', 'SOLD_OUT', 2),
       (3, 3, 3, '유저3', '다이아검', 2000, 'SWORD', '일반 다이아검', 'WAIT', 3),
       (3, 4, 4, '유저3', '강철도끼', 1500, 'AXE', '일반 강철도끼', 'SELLING', 3);

insert into item(name, type, price, description)
values ('강철검', 'SWORD', 7000, '일반 강철검'),
       ('강철도끼', 'AXE', 1500, '일반 강철도끼'),
       ('great sword', 'SWORD', 15000, '대단한 검'),
       ('다이아창', 'SPEAR', 3000, '레어 다이아창');

insert into item_search_keyword(item_keyword, item_search_count, version)
values ('가 창', 3, 1),
       ('나 검', 10, 1),
       ('라 검', 4, 1),
       ('다 도끼', 4, 1),
       ('마 검', 2, 1),
       ('바 도끼', 1, 1);

insert into item_transaction(member_id, inventory_id, item_id, total_price, total_quantity, status, ordered_at)
values (1, 1, 1, 1000, 200, 'COMPLETED', '2024-09-8T15:43:02.842110400'),
       (1, 1, 1, 1000, 200, 'COMPLETED', '2024-09-8T15:43:02.842110400'),
       (1, 1, 2, 1000, 350, 'COMPLETED', '2024-09-8T15:43:02.842110400'),
       (1, 1, 3, 1000, 100, 'COMPLETED', '2024-09-8T15:43:02.842110400'),
       (1, 1, 4, 1000, 100, 'COMPLETED', '2024-09-8T15:43:02.842110400');