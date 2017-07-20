SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE `account`;
TRUNCATE TABLE `transaction`;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (1, '1', 'popo man', '2017-06-10 16:22:00', 750000.49, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (2, '2', 'nisim garame', '2017-7-05 21:52:00',120.00, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (3, '3', 'sami vesusu', '2017-03-12 09:02:00',35147.59, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (4, '4', 'hakabay hagibor', '2017-06-01 13:21:00',12760.25, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (5, '5', 'yuval hamebulbal', '2017-05-21 21:13:00',-2395.11, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (6, '6', 'habil hagarbil', '2017-06-17 19:27:21',63263.79, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (7, '7', 'utzli gutzli', '2017-05-08 14:32:00',5642.74, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (8, '8', 'tuty fruti', '2017-04-09 23:07:00',5635.73, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (9, '9', 'rambo man', '2017-02-16 13:25:00',-35434.23, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (10, '10', 'shuna danuna', '2017-03-23 22:12:15',4674545.00, 'blat 12', 'opop@gmail.com');
INSERT INTO `account` (`id`, `user_id`, `full_name`, `creation_date`, `balance`, `address`, `email`) VALUES (11, '11', 'paspar tu', '2017-04-27 22:39:00',45345.06, 'blat 12', 'opop@gmail.com');

INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`, `date`, `description`) VALUES ('1', 120, 1, 2, '2017-09-10 16:22:00', 'enjoy!');
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`, `date`, `description`) VALUES ('2', 120, 1, 3, '2017-08-2 16:22:00', 'good luck!');
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`, `date`, `description`) VALUES ('3', 120, 2, 10, '2017-08-28 16:22:00', 'for you!');
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`, `date`, `description`) VALUES ('4', 120, 9, 4, '2017-04-12 16:22:00', '');
INSERT INTO `transaction` (`id`, `amount`, `from_account_id`, `to_account_id`, `date`, `description`) VALUES ('5', 120, 7, 6, '2017-07-14 16:22:00', 'moneyyyy');

#CREATE INDEX balance ON `account`(`balance`,`userid`);