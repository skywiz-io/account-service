SET FOREIGN_KEY_CHECKS=0;
TRUNCATE `account`;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (1, '1', 'popo', '10/7/2017 16:21',750000.49);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (2, '2', 'nisim', '5/7/2017 16:21',120.00);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (3, '3', 'sami', '12/3/2016 16:21',35147.59);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (4, '4', 'hakabay', '1/2/2017 16:21',12760.25);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (5, '5', 'yuval', '21/5/2017 16:21',-2395.11);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (6, '6', 'hamebulbal', '17/6/2017 16:21',63263.79);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (7, '7', 'utzli', '8/5/2017 16:21',5642.74);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (8, '8', 'gutzli', '9/4/2017 16:21',5635.73);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (9, '9', 'rambo', '16/2/2017 16:21',-35434.23);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (10, '10', 'shuna', '23/3/2017 16:21',4674545.00);
INSERT INTO `account` (`id`, `userid`, `name`, `creationdate`, `balance`) VALUES (11, '11', 'paspartu', '27/4/2017 16:21',45345.06);


#CREATE INDEX balance ON `account`(`balance`,`userid`);