-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 18-Abr-2017 às 17:05
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `equipamentos`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_valida_usuario` (`p_login` VARCHAR(12), `p_senha` VARCHAR(50)) RETURNS INT(1) BEGIN  
 DECLARE l_ret            INT(1) DEFAULT 0;  
     SET l_ret = IFNULL((SELECT DISTINCT 1  
                       FROM usuario  
                      WHERE login = p_login  
                       AND password = MD5(p_senha)),0);                           
 RETURN l_ret;  
 END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `componente`
--

CREATE TABLE `componente` (
  `num_comp` int(5) NOT NULL COMMENT 'Número do Componente',
  `num_serie` int(5) NOT NULL COMMENT 'Número de Serie',
  `descricao` varchar(40) NOT NULL COMMENT 'Descrição do Componente',
  `fabricante` varchar(40) NOT NULL COMMENT 'Fabricante do Componente',
  `modelo` varchar(40) NOT NULL COMMENT 'Modelo do Componente',
  `procedencia` varchar(40) NOT NULL COMMENT 'Procedência do Componente'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `componente`
--

INSERT INTO `componente` (`num_comp`, `num_serie`, `descricao`, `fabricante`, `modelo`, `procedencia`) VALUES
(1, 1, 'Tamanho Compacto', 'Lexmark', 'x650', 'Contrato'),
(2, 2, 'Multifuncional', 'HP', '30000-1', 'Loja'),
(3, 3, 'Full HD', 'AOC', '30000-1', 'Loja'),
(4, 2, 'Mecanico', 'Maxxtro', '2154-21', 'Loja'),
(5, 2, 'Nikomarx', '---', 'Desktop', 'boadica.com.br'),
(6, 17, 'Full HD', 'LG', '2154-0', 'Casas Bahia'),
(7, 15, 'Voa', 'Wise Case', '24251', 'Centro Stand 521'),
(8, 21, 'Ultra Rapido', 'HP', '12151', 'GG'),
(9, 1515, 'Botao duplo clique, 2 adicionais e DPI', 'A4 Tech', '2012', 'MercadoLivre');

-- --------------------------------------------------------

--
-- Estrutura da tabela `defeito`
--

CREATE TABLE `defeito` (
  `codigo` int(5) NOT NULL COMMENT 'Código do Defeito',
  `tipo` varchar(40) NOT NULL COMMENT 'Tipo do Defeito'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `defeito`
--

INSERT INTO `defeito` (`codigo`, `tipo`) VALUES
(1, 'Configuração'),
(2, 'Cartucho');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gabinete`
--

CREATE TABLE `gabinete` (
  `num_comp` int(5) NOT NULL COMMENT 'Código do Componente',
  `codigo` int(5) NOT NULL COMMENT 'Codigo do Gabinete',
  `pl_mae` varchar(40) NOT NULL COMMENT 'Placa Mãe',
  `hd` varchar(40) NOT NULL COMMENT 'Hard Disk',
  `pl_rede` varchar(40) NOT NULL COMMENT 'Placa de Rede',
  `memoria` varchar(40) NOT NULL COMMENT 'Memória',
  `disquete` varchar(40) NOT NULL COMMENT 'Disquete ou Leitor de Cartão',
  `pl_video` varchar(40) NOT NULL COMMENT 'Placa de Vídeo',
  `cd_rom` varchar(40) NOT NULL COMMENT 'Cd-Rom',
  `processador` varchar(40) NOT NULL COMMENT 'CPU'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gabinete`
--

INSERT INTO `gabinete` (`num_comp`, `codigo`, `pl_mae`, `hd`, `pl_rede`, `memoria`, `disquete`, `pl_video`, `cd_rom`, `processador`) VALUES
(5, 1, 'GIGABYTE 78LMT-S2P', 'SAMSUNG HD103SI ATA DEVICE', 'Qualcomm Atheros AR8151', '2x Kingston 4GB', '---', 'NVIDIA GeForce GTX 650 TI', '---', 'AMD FX-6100 Six-Core'),
(7, 2, 'Asrock A55H', 'WD 500GB', 'Qualquer uma', 'Kingston 4GB', 'Não possui', 'AMD 6570', 'LG', 'AMD A6');

-- --------------------------------------------------------

--
-- Estrutura da tabela `impressora`
--

CREATE TABLE `impressora` (
  `num_comp` int(5) NOT NULL COMMENT 'Número do componente',
  `codigo` int(5) NOT NULL COMMENT 'Código da Impressora',
  `velocidade` int(5) NOT NULL COMMENT 'Velocidade da Impressora'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `impressora`
--

INSERT INTO `impressora` (`num_comp`, `codigo`, `velocidade`) VALUES
(1, 1, 4),
(2, 2, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `instalacao`
--

CREATE TABLE `instalacao` (
  `codigo_software` int(5) NOT NULL COMMENT 'Código do Software',
  `codigo_maquina` int(5) NOT NULL COMMENT 'Código da Máquina',
  `data_instalacao` varchar(10) NOT NULL COMMENT 'Data da Instalação'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `instalacao`
--

INSERT INTO `instalacao` (`codigo_software`, `codigo_maquina`, `data_instalacao`) VALUES
(2, 1, '19/11/2014'),
(1, 3, '19/11/2014');

-- --------------------------------------------------------

--
-- Estrutura da tabela `maquina`
--

CREATE TABLE `maquina` (
  `codigo` int(5) NOT NULL COMMENT 'Código da Máquina',
  `cod_monitor` int(5) NOT NULL COMMENT 'Código do Monitor',
  `cod_teclado` int(5) NOT NULL COMMENT 'Código do Teclado',
  `cod_gabinete` int(5) NOT NULL COMMENT 'Código do Gabinete',
  `cod_impressora` int(5) NOT NULL COMMENT 'Código da Impressora',
  `cod_scanner` int(5) NOT NULL COMMENT 'Código do Scanner',
  `cod_mouse` int(5) NOT NULL COMMENT 'Código do Mouse'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `maquina`
--

INSERT INTO `maquina` (`codigo`, `cod_monitor`, `cod_teclado`, `cod_gabinete`, `cod_impressora`, `cod_scanner`, `cod_mouse`) VALUES
(1, 1, 1, 1, 1, 1, 1),
(2, 2, 1, 2, 1, 1, 1),
(3, 2, 1, 2, 2, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `monitor`
--

CREATE TABLE `monitor` (
  `num_comp` int(5) NOT NULL COMMENT 'Numero do Componente',
  `codigo` int(5) NOT NULL COMMENT 'Código do Monitor',
  `polegadas` int(2) NOT NULL COMMENT 'Tamanho do Monitor'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `monitor`
--

INSERT INTO `monitor` (`num_comp`, `codigo`, `polegadas`) VALUES
(3, 1, 22),
(6, 2, 32);

-- --------------------------------------------------------

--
-- Estrutura da tabela `mouse`
--

CREATE TABLE `mouse` (
  `num_comp` int(5) NOT NULL COMMENT 'Código do Componente',
  `codigo` int(5) NOT NULL COMMENT 'Código do Mouse'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `mouse`
--

INSERT INTO `mouse` (`num_comp`, `codigo`) VALUES
(9, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ordem_servico`
--

CREATE TABLE `ordem_servico` (
  `num_comp` int(5) NOT NULL COMMENT 'Número do Componente',
  `codigo_defeito` int(5) NOT NULL COMMENT 'Código do Defeito',
  `data_abertura` varchar(10) NOT NULL COMMENT 'Data da Abertura',
  `data_fechamento` varchar(10) NOT NULL COMMENT 'Data do Fechamento',
  `estado` varchar(40) NOT NULL COMMENT 'Estado do Componente',
  `solucao` varchar(40) NOT NULL COMMENT 'Solução do Defeito'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ordem_servico`
--

INSERT INTO `ordem_servico` (`num_comp`, `codigo_defeito`, `data_abertura`, `data_fechamento`, `estado`, `solucao`) VALUES
(1, 2, '21/11/2014', '22/11/2014', 'Sem Tinta', 'Compra de Cartuchos novos'),
(9, 1, 'hoje', 'ho9je', 'RJ', 'n sei');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sala`
--

CREATE TABLE `sala` (
  `codigo` int(5) NOT NULL COMMENT 'Código da Sala',
  `nome` varchar(40) NOT NULL COMMENT 'Nome da Sala'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `sala`
--

INSERT INTO `sala` (`codigo`, `nome`) VALUES
(1, 'Laboratorio 1'),
(2, 'Redes');

-- --------------------------------------------------------

--
-- Estrutura da tabela `scanner`
--

CREATE TABLE `scanner` (
  `num_comp` int(5) NOT NULL COMMENT 'Código do Componente',
  `codigo` int(5) NOT NULL COMMENT 'Código do Scanner'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `scanner`
--

INSERT INTO `scanner` (`num_comp`, `codigo`) VALUES
(8, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `sm`
--

CREATE TABLE `sm` (
  `codigo_sala` int(5) NOT NULL COMMENT 'Código da Sala',
  `codigo_maquina` int(5) NOT NULL COMMENT 'Código da Máquina'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `sm`
--

INSERT INTO `sm` (`codigo_sala`, `codigo_maquina`) VALUES
(2, 1),
(1, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `software`
--

CREATE TABLE `software` (
  `codigo` int(5) NOT NULL COMMENT 'Código do Software',
  `nome` varchar(40) NOT NULL COMMENT 'Nome do Software',
  `licenca` varchar(40) NOT NULL COMMENT 'Licença do Software'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `software`
--

INSERT INTO `software` (`codigo`, `nome`, `licenca`) VALUES
(1, 'Windows 8.1 Profissional 2457', '00178-10323-92050-AA095'),
(2, 'Skype', 'XXXX-XXX-XXX-XXXX-XX');

-- --------------------------------------------------------

--
-- Estrutura da tabela `teclado`
--

CREATE TABLE `teclado` (
  `num_comp` int(5) NOT NULL COMMENT 'Número do Componente',
  `codigo` int(5) NOT NULL COMMENT 'Código do Teclado'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `teclado`
--

INSERT INTO `teclado` (`num_comp`, `codigo`) VALUES
(4, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `codigo` int(10) NOT NULL COMMENT 'Código do Tipo',
  `descricao` varchar(40) NOT NULL COMMENT 'Qual o tipo'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`codigo`, `descricao`) VALUES
(1, 'Adminstrador'),
(2, 'Padrão');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `nome` varchar(40) NOT NULL COMMENT 'Nome do Usuario',
  `login` varchar(12) NOT NULL COMMENT 'Login do Usuario',
  `password` varchar(50) NOT NULL COMMENT 'Senha do Usuario',
  `codigo_tipo_usuario` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`nome`, `login`, `password`, `codigo_tipo_usuario`) VALUES
('Paulo Henrique', '35750500', '716bd7df77321c03fd2c0fe20ab14e2a', 1),
('Adminstrador', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `componente`
--
ALTER TABLE `componente`
  ADD PRIMARY KEY (`num_comp`);

--
-- Indexes for table `defeito`
--
ALTER TABLE `defeito`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `gabinete`
--
ALTER TABLE `gabinete`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `num_comp` (`num_comp`);

--
-- Indexes for table `impressora`
--
ALTER TABLE `impressora`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `num_comp_imp` (`num_comp`);

--
-- Indexes for table `instalacao`
--
ALTER TABLE `instalacao`
  ADD PRIMARY KEY (`codigo_maquina`),
  ADD KEY `codigo_software` (`codigo_software`);

--
-- Indexes for table `maquina`
--
ALTER TABLE `maquina`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `cod_monitor` (`cod_monitor`,`cod_teclado`,`cod_gabinete`,`cod_impressora`,`cod_scanner`,`cod_mouse`),
  ADD KEY `cod_teclado` (`cod_teclado`),
  ADD KEY `cod_gabinete` (`cod_gabinete`),
  ADD KEY `cod_impressora` (`cod_impressora`),
  ADD KEY `cod_scanner` (`cod_scanner`),
  ADD KEY `cod_mouse` (`cod_mouse`);

--
-- Indexes for table `monitor`
--
ALTER TABLE `monitor`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `num_comp` (`num_comp`);

--
-- Indexes for table `mouse`
--
ALTER TABLE `mouse`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `num_comp` (`num_comp`);

--
-- Indexes for table `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD PRIMARY KEY (`num_comp`,`codigo_defeito`),
  ADD KEY `codigo_defeito` (`codigo_defeito`);

--
-- Indexes for table `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `scanner`
--
ALTER TABLE `scanner`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `num_comp` (`num_comp`);

--
-- Indexes for table `sm`
--
ALTER TABLE `sm`
  ADD PRIMARY KEY (`codigo_sala`,`codigo_maquina`),
  ADD KEY `codigo_maquina` (`codigo_maquina`);

--
-- Indexes for table `software`
--
ALTER TABLE `software`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `teclado`
--
ALTER TABLE `teclado`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `num_comp` (`num_comp`);

--
-- Indexes for table `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`login`),
  ADD KEY `codigo_tipo_usuario` (`codigo_tipo_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `codigo` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Código do Tipo', AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `gabinete`
--
ALTER TABLE `gabinete`
  ADD CONSTRAINT `gabinete_ibfk_1` FOREIGN KEY (`num_comp`) REFERENCES `componente` (`num_comp`);

--
-- Limitadores para a tabela `impressora`
--
ALTER TABLE `impressora`
  ADD CONSTRAINT `impressora_ibfk_1` FOREIGN KEY (`num_comp`) REFERENCES `componente` (`num_comp`);

--
-- Limitadores para a tabela `instalacao`
--
ALTER TABLE `instalacao`
  ADD CONSTRAINT `instalacao_ibfk_1` FOREIGN KEY (`codigo_software`) REFERENCES `software` (`codigo`),
  ADD CONSTRAINT `instalacao_ibfk_2` FOREIGN KEY (`codigo_maquina`) REFERENCES `maquina` (`codigo`);

--
-- Limitadores para a tabela `maquina`
--
ALTER TABLE `maquina`
  ADD CONSTRAINT `maquina_ibfk_1` FOREIGN KEY (`cod_monitor`) REFERENCES `monitor` (`codigo`),
  ADD CONSTRAINT `maquina_ibfk_2` FOREIGN KEY (`cod_teclado`) REFERENCES `teclado` (`codigo`),
  ADD CONSTRAINT `maquina_ibfk_3` FOREIGN KEY (`cod_impressora`) REFERENCES `impressora` (`codigo`),
  ADD CONSTRAINT `maquina_ibfk_4` FOREIGN KEY (`cod_gabinete`) REFERENCES `gabinete` (`codigo`),
  ADD CONSTRAINT `maquina_ibfk_5` FOREIGN KEY (`cod_scanner`) REFERENCES `scanner` (`codigo`),
  ADD CONSTRAINT `maquina_ibfk_6` FOREIGN KEY (`cod_mouse`) REFERENCES `mouse` (`codigo`);

--
-- Limitadores para a tabela `monitor`
--
ALTER TABLE `monitor`
  ADD CONSTRAINT `monitor_ibfk_1` FOREIGN KEY (`num_comp`) REFERENCES `componente` (`num_comp`);

--
-- Limitadores para a tabela `mouse`
--
ALTER TABLE `mouse`
  ADD CONSTRAINT `mouse_ibfk_1` FOREIGN KEY (`num_comp`) REFERENCES `componente` (`num_comp`);

--
-- Limitadores para a tabela `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD CONSTRAINT `ordem_servico_ibfk_1` FOREIGN KEY (`num_comp`) REFERENCES `componente` (`num_comp`),
  ADD CONSTRAINT `ordem_servico_ibfk_2` FOREIGN KEY (`codigo_defeito`) REFERENCES `defeito` (`codigo`);

--
-- Limitadores para a tabela `scanner`
--
ALTER TABLE `scanner`
  ADD CONSTRAINT `scanner_ibfk_1` FOREIGN KEY (`num_comp`) REFERENCES `componente` (`num_comp`);

--
-- Limitadores para a tabela `sm`
--
ALTER TABLE `sm`
  ADD CONSTRAINT `sm_ibfk_1` FOREIGN KEY (`codigo_sala`) REFERENCES `sala` (`codigo`),
  ADD CONSTRAINT `sm_ibfk_2` FOREIGN KEY (`codigo_maquina`) REFERENCES `maquina` (`codigo`);

--
-- Limitadores para a tabela `teclado`
--
ALTER TABLE `teclado`
  ADD CONSTRAINT `teclado_ibfk_1` FOREIGN KEY (`num_comp`) REFERENCES `componente` (`num_comp`);

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`codigo_tipo_usuario`) REFERENCES `tipo_usuario` (`codigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
