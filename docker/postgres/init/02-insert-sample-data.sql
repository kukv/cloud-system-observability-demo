-- Insert sample users
insert into users.user_id (id)
values  (1),
        (2),
        (3),
        (4),
        (5);
select setval(pg_get_serial_sequence('users.user_id', 'id'), (select max(id) from users.user_id));

insert into users.profile (user_id, first_name, last_name)
values  (1, '山田', '太郎'),
        (2, '加藤', '幸平'),
        (3, '田中', '幸子'),
        (4, '工藤', '新一'),
        (5, '佐藤', '次郎');

insert into users.contact (user_id, mail_address, phone_number)
values  (1, 'yamada.taro@example.com', '09012345678'),
        (2, 'kato.kohei@example.com', '09087654321'),
        (3, 'tanaka.sachiko@example.com', '08012345678'),
        (4, 'kudo.shinichi@example.com', '08087654321'),
        (5, 'sato.jiro@example.com', '07012345678');

insert into users.address (user_id, postal_code, prefecture, city, address_line, building_name, building_number)
VALUES  (1, '0600001', '北海道', '札幌市中央区', '北1条西2丁目1-1', '札幌セントラルビル', '101'),
        (2, '1638001', '東京都', '新宿区', '西新宿2丁目11-1', '新宿タワー', '3003'),
        (3, '4600001', '愛知県', '名古屋市中区', '三の丸3丁目1-2', '名古屋城前レジデンス', '801'),
        (4, '5400008', '大阪府', '大阪市中央区', '大手前2丁目3-1', 'ナニワビル', '12F'),
        (5, '8100001', '福岡県', '福岡市中央区', '天神1丁目7-1', '天神コアレジデンス', '902');

-- Insert sample products
insert into products.product_id (id)
values  (1),
        (2),
        (3),
        (4),
        (5),
        (6),
        (7),
        (8),
        (9),
        (10);
select setval(pg_get_serial_sequence('products.product_id', 'id'), (select max(id) from products.product_id));

insert into products.overview (product_id, name, description)
values  (1, '高性能ノートPC', 'プロフェッショナル向けのハイスペックなノートパソコン'),
        (2, '最新スマートフォン', '高度な機能を搭載した最新モデルのスマートフォン'),
        (3, 'ワイヤレスヘッドホン', 'ノイズキャンセリング機能を備えたワイヤレスヘッドホン'),
        (4, '10インチタブレット', '高解像度ディスプレイを搭載した10インチタブレット'),
        (5, 'スマートウォッチ', 'フィットネス管理機能付きのスマートウォッチ'),
        (6, 'メカニカルキーボード', 'RGBライティングを搭載したゲーミング用メカニカルキーボード'),
        (7, 'エルゴノミクスマウス', '人間工学に基づいた設計のワイヤレスマウス'),
        (8, '27インチ4Kモニター', 'クリエイティブ作業に最適な27インチの高精細4Kモニター'),
        (9, 'デジタルカメラ', '1200万画素のセンサーを搭載した高品質デジタルカメラ'),
        (10, 'ワイヤレススピーカー', '高音質オーディオを実現するワイヤレススピーカー');

insert into products.price (product_id, price)
values  (1, 198000), -- 高性能ノートPC
        (2, 124800), -- 最新スマートフォン
        (3, 34800),  -- ワイヤレスヘッドホン
        (4, 42000),  -- 10インチタブレット
        (5, 29800),  -- スマートウォッチ
        (6, 15800),  -- メカニカルキーボード
        (7, 8500),   -- エルゴノミクスマウス
        (8, 59800),  -- 27インチ4Kモニター
        (9, 88000),  -- デジタルカメラ
        (10, 19800); -- ワイヤレススピーカー

insert into products.stock (product_id, quantity)
values  (1, 50),
        (2, 100),
        (3, 75),
        (4, 60),
        (5, 80),
        (6, 120),
        (7, 150),
        (8, 40),
        (9, 100),
        (10, 200);

-- Insert sample orders
insert into orders.order_id (id, user_id)
values  (1, 1), -- 山田さんの注文
        (2, 2), -- 加藤さんの注文
        (3, 3); -- 田中さんの注文
select setval(pg_get_serial_sequence('orders.order_id', 'id'), (select max(id) from orders.order_id));

insert into orders.shipping_address (order_id, postal_code, address_full)
values  (1, '0600001', '北海道札幌市中央区北1条西2丁目1-1 札幌セントラルビル 101'),
        (2, '1638001', '東京都新宿区西新宿2丁目11-1 新宿タワー 3003'),
        (3, '4600001', '愛知県名古屋市中区三の丸3丁目1-2 名古屋城前レジデンス 801');

insert into orders.item (order_id, product_id, unit_price, quantity)
values  (1, 1, 198000, 1),
        (1, 7, 8500, 1),  -- 山田さんの注文: ノートPC(ID:1)とマウス(ID:7)
        (2, 2, 124800, 2), -- 加藤さんの注文: スマホ(ID:2)を2台
        (3, 3, 34800, 1); -- 田中さんの注文: ヘッドホン(ID:3)を1台

insert into orders.status (order_id, status_code)
values  (1, 'ORDERED'),
        (2, 'ORDERED'),
        (3, 'ORDERED'),
        (3, 'CANCELLED'); -- 田中さんはキャンセル

-- 山田さんの注文分
insert into products.stock (product_id, quantity) values (1, 49); -- 50 -> 49
insert into products.stock (product_id, quantity) values (7, 149); -- 150 -> 149

-- 加藤さんの注文分
insert into products.stock (product_id, quantity) values (2, 98); -- 100 -> 98

-- 田中さんの注文分（キャンセル前）
insert into products.stock (product_id, quantity) values (3, 74); -- 75 -> 74
