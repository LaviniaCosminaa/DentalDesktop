-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2022 at 12:33 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dental`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `ID` int(11) NOT NULL,
  `DATE` varchar(20) NOT NULL,
  `HOUR` varchar(10) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `TOOTH` varchar(50) NOT NULL,
  `PATIENT_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`ID`, `DATE`, `HOUR`, `DESCRIPTION`, `TOOTH`, `PATIENT_ID`) VALUES
(1, '04/01/2022', '10:00', '**Consultation**', '2.4, 2.5', 1),
(2, '04/01/2022', '10:30', 'Consultation', '3.6', 2),
(3, '03/01/2022', '09:00', 'Consultation', '2.6', 3),
(4, '05/01/2022', '18:00', 'Consultation', '3.6, 3.7', 4),
(5, '06/01/2022', '13:00', 'Extraction', '3.6', 4),
(6, '08/01/2022', '09:00', 'Extraction', '4.6', 2),
(7, '30/12/2021', '18:00', 'Consultation', '2.7', 3),
(8, '30/12/2021', '17:00', '***Consultation', '3.5', 1),
(9, '20/12/2021', '13:00', 'Extraction', '3.5', 4),
(10, '11/01/2022', '16:00', 'Consultation', '2.8', 5),
(11, '04/01/2022', '15:00', 'Consultation', '2.6', 6),
(12, '12/01/2021', '13:00', '--', '3.5, 3.6', 6),
(13, '04/02/2022', '14:00', '-', '4.7', 7),
(18, '07/01/2022', '12:00', 'Consultation', '2.7', 10);

-- --------------------------------------------------------

--
-- Table structure for table `cabinet`
--

CREATE TABLE `cabinet` (
  `ID` int(11) NOT NULL,
  `CABINET NAME` text NOT NULL,
  `DOCTOR NAME` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cabinet`
--

INSERT INTO `cabinet` (`ID`, `CABINET NAME`, `DOCTOR NAME`) VALUES
(1, 'CMIFS', 'FS/CC');

-- --------------------------------------------------------

--
-- Table structure for table `medrecords`
--

CREATE TABLE `medrecords` (
  `ID` int(11) NOT NULL,
  `ALLERGIES` varchar(50) NOT NULL,
  `TREATMENT` varchar(50) NOT NULL,
  `LAST TREATMENT DATE` varchar(20) NOT NULL,
  `OTHER SURGERY` varchar(50) NOT NULL,
  `VICES` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medrecords`
--

INSERT INTO `medrecords` (`ID`, `ALLERGIES`, `TREATMENT`, `LAST TREATMENT DATE`, `OTHER SURGERY`, `VICES`) VALUES
(1, 'No', 'No', '13/11/2021', 'No', 'No'),
(2, 'No', 'No', '06/08/2021', 'No', 'No'),
(3, 'No', 'No', '10/02/2019', 'No', 'Smoking'),
(4, 'No', 'No', '17/10/2021', 'No', 'No'),
(5, 'No', 'No', '12/10/2021', 'No', 'No'),
(6, 'No', 'No', '12/12/2019', 'No', 'No'),
(7, 'No', 'No', '12/11/2021', 'No', 'No'),
(8, 'No', 'No', '09/10/2021', 'No', 'No'),
(9, 'No', 'No', '12/08/2021', 'No', 'No'),
(10, 'No', 'Paracetamol', '04/01/2022', 'No', 'No'),
(11, 'No', 'No', '02/12/2021', 'No', 'No');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `AGE` int(4) NOT NULL,
  `GENDER` char(1) NOT NULL,
  `PHONE NUMBER` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`ID`, `NAME`, `AGE`, `GENDER`, `PHONE NUMBER`) VALUES
(1, 'Popescu Maria', 19, 'F', '0123456789'),
(2, 'Ionescu Mihai', 25, 'M', '0987654321'),
(3, 'Vladimirescu Ionut', 35, 'M', '0897654321'),
(4, 'Mercescu Ionut', 21, 'M', '0234516789'),
(5, 'Sarbu Ioana', 27, 'F', '0123459876'),
(6, 'Sarbulescu Matei', 46, 'M', '0922673830'),
(7, 'Nume prenume', 17, 'F', '0987654321'),
(8, 'Floruta Raul', 20, 'M', '0123454321'),
(9, 'Cosmina Lavinia', 19, 'F', '0746554039'),
(10, 'Filip Bogdan', 20, 'M', '0770101322'),
(11, 'Popescu Ana Maria', 17, 'F', '0999988876');

-- --------------------------------------------------------

--
-- Table structure for table `sterilizations`
--

CREATE TABLE `sterilizations` (
  `ID` int(11) NOT NULL,
  `DATE` char(10) NOT NULL,
  `BEGIN` char(5) NOT NULL,
  `END` char(5) NOT NULL,
  `DEVICE` varchar(10) NOT NULL,
  `CONTENT` varchar(500) NOT NULL,
  `NAME` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sterilizations`
--

INSERT INTO `sterilizations` (`ID`, `DATE`, `BEGIN`, `END`, `DEVICE`, `CONTENT`, `NAME`) VALUES
(1, '02/01/2022', '12:00', '13:00', 'Autoclav', 'content content content', 'F.S.'),
(2, '03/01/2022', '13:00', '14:00', 'Autoclav', 'content content contentcontentcontent content content\ncontentcontentcontent content content content', 'F.S.'),
(3, '03/01/2022', '12:00', '13:00', 'Autoclav', 'content contentcontentcontentcontent contentcontent\ncontent content content content content content content\ncontent contentcontent contentcontent contentcontent', 'F.S.'),
(4, '04/01/2022', '12:00', '13:00', 'Pupinel', 'content content content contentcontentcontentcontent content\ncontentcontentcontent contentcontent contentcontent', 'F.S.'),
(5, '04/01/2022', '09:00', '10:00', 'Autoclav', 'conteeeeeent conteeeeeent conteeeeeentconteeeeeent ', 'F.S.'),
(6, '04/01/2022', '09:00', '10:00', 'Pupinel', ' content contentcontentcontentcontent content\ncontentcontentcontent contentcontent\ncontentcontentcontentcontent contentcontent\ncontent contentcontentcontent', 'F.S.');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `ID` int(11) NOT NULL,
  `INSTRUMENT` varchar(50) NOT NULL,
  `DETAILS` varchar(300) NOT NULL,
  `PIECES` int(11) NOT NULL,
  `PRICE/PIECE` int(11) NOT NULL,
  `LAST STOCK DATE` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`ID`, `INSTRUMENT`, `DETAILS`, `PIECES`, `PRICE/PIECE`, `LAST STOCK DATE`) VALUES
(1, 'Ac', '***', 600, 1, '02/01/2022'),
(2, 'Chiureta de os', '****', 400, 5, '12/11/2021'),
(3, 'Chiureta Gracey', '****', 400, 8, '15/11/2021'),
(4, 'Elevator', '****', 400, 32, '02/01/2022'),
(5, 'Freza sferica', 'extra-dura', 400, 3, '01/01/2022'),
(6, 'Freza conica', '@@@', 400, 20, '04/01/2022'),
(7, 'Freza con invers', ')))))', 599, 16, '03/01/2022'),
(8, 'Freza ', '!!!!!', 289, 20, '03/01/2022');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `PATIENT_ID` (`PATIENT_ID`);

--
-- Indexes for table `cabinet`
--
ALTER TABLE `cabinet`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `medrecords`
--
ALTER TABLE `medrecords`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sterilizations`
--
ALTER TABLE `sterilizations`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `cabinet`
--
ALTER TABLE `cabinet`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `medrecords`
--
ALTER TABLE `medrecords`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `sterilizations`
--
ALTER TABLE `sterilizations`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patients` (`ID`);

--
-- Constraints for table `medrecords`
--
ALTER TABLE `medrecords`
  ADD CONSTRAINT `medrecords_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `patients` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
