-- create sample_transaction_logs for just example.
CREATE TABLE `sample_transaction_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total_received` int(11) DEFAULT NULL,
  `total_sent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
