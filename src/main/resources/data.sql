SET FOREIGN_KEY_CHECKS=0;
TRUNCATE `account`;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (1, '1', 'popo', '2017-06-10 16:22:00',750000.49);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (2, '2', 'nisim', '2017-7-05 21:52:00',120.00);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (3, '3', 'sami', '2017-03-12 09:02:00',35147.59);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (4, '4', 'hakabay', '2017-06-01 13:21:00',12760.25);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (5, '5', 'yuval', '2017-05-21 21:13:00',-2395.11);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (6, '6', 'hamebulbal', '2017-06-17 19:27:21',63263.79);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (7, '7', 'utzli', '2017-05-08 14:32:00',5642.74);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (8, '8', 'gutzli', '2017-04-09 23:07:00',5635.73);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (9, '9', 'rambo', '2017-02-16 13:25:00',-35434.23);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (10, '10', 'shuna', '2017-03-23 22:12:15',4674545.00);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (11, '11', 'paspartu', '2017-04-27 22:39:00',45345.06);


#CREATE INDEX balance ON `account`(`balance`,`userid`);