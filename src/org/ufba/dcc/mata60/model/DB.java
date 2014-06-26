package org.ufba.dcc.mata60.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {
	
	private static Connection connection;
	
	//configuration of the database driver
	private static DB db = null;
	private static String user = "root";
	private static String password = "newadmin";
	private static String url = "jdbc:mysql://localhost/";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//for create schema and tables
	private static final String DB_NAME = "monitoria";
	
	private static final String DEPARTAMENTO = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`departamento` ("+
		  "`cod` INT NOT NULL AUTO_INCREMENT,"+
		  "`nome` VARCHAR(45) NOT NULL,"+
		  "PRIMARY KEY (`cod`),"+
		  "UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))"+
		  "ENGINE = InnoDB;";
		
	private static final String DISCIPLINA = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`disciplina` ("+
		  "`cod` VARCHAR(10) NOT NULL,"+
		  "`nome` VARCHAR(45) NOT NULL,"+
		  "`departamento_cod` INT NOT NULL,"+
		  "PRIMARY KEY (`cod`),"+
		  "INDEX `fk_disciplina_departamento_idx` (`departamento_cod` ASC),"+
		  "CONSTRAINT `fk_disciplina_departamento`"+
		    "FOREIGN KEY (`departamento_cod`)"+
		    "REFERENCES "+DB_NAME+".`departamento` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		  "ENGINE = InnoDB;";
	
	private static final String ALUNO = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`aluno` ("+
		  "`cpf` VARCHAR(11) NOT NULL,"+
		  "`matricula` VARCHAR(9) NOT NULL,"+
		  "`nome` VARCHAR(65) NOT NULL,"+
		  "PRIMARY KEY (`cpf`),"+
		  "UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC))"+
		"ENGINE = InnoDB;";

	private static final String PROFESSOR = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`professor` ("+
		  "`cpf` VARCHAR(11) NOT NULL,"+
		  "`matricula` VARCHAR(20) NOT NULL,"+
		  "`nome` VARCHAR(45) NOT NULL,"+
		  "`departamento_cod` INT NOT NULL,"+
		  "`tipo` VARCHAR(4) NOT NULL,"+
		  "PRIMARY KEY (`cpf`),"+
		  "UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC),"+
		  "INDEX `fk_professor_departamento1_idx` (`departamento_cod` ASC),"+
		  "CONSTRAINT `fk_professor_departamento1`"+
		    "FOREIGN KEY (`departamento_cod`)"+
		    "REFERENCES "+DB_NAME+".`departamento` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION,"+
		    "CHECK(tipo in(\"DE\", \"20H\", \"40H\")))"+ //TODO esta restrição não funciona!
		"ENGINE = InnoDB;";
	
	private static final String TURMA = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`turma` ("+
		  "`numero` VARCHAR(6) NOT NULL,"+
		  "`hora_inicio` TIME NOT NULL,"+
		  "`hora_fim` TIME NOT NULL,"+
		  "`data_inicio` DATE NULL,"+
		  "`semestre` VARCHAR(6) NOT NULL,"+
		  "`disciplina_cod` VARCHAR(10) NOT NULL,"+
		  "PRIMARY KEY (`numero`, `disciplina_cod`, `semestre`),"+
		  "INDEX `fk_turma_disciplina1_idx` (`disciplina_cod` ASC),"+
		  "CONSTRAINT `fk_turma_disciplina1`"+
		    "FOREIGN KEY (`disciplina_cod`)"+
		    "REFERENCES "+DB_NAME+".`disciplina` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE CASCADE)"+
		"ENGINE = InnoDB;";
	
	private static final String PROJETO = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`projeto` ("+
		  "`cod` INT NOT NULL,"+
		  "`ata_aprovacao` TEXT NULL,"+
		  "`data_approvacao` DATE NULL,"+
		  "`descricao` TEXT NOT NULL,"+
		  "`turma_numero` VARCHAR(6) NOT NULL,"+
		  "`professor_cpf` VARCHAR(11) NOT NULL,"+
		  "`turma_disciplina_cod` VARCHAR(10) NOT NULL,"+
		  "`turma_semestre` VARCHAR(6) NOT NULL,"+
		  "PRIMARY KEY (`cod`),"+
		  "INDEX `fk_projeto_turma1_idx` (`turma_numero` ASC, `turma_disciplina_cod` ASC, `turma_semestre` ASC),"+
		  "INDEX `fk_projeto_professor1_idx` (`professor_cpf` ASC),"+
		  "CONSTRAINT `fk_projeto_turma1`"+
		    "FOREIGN KEY (`turma_numero` , `turma_disciplina_cod` , `turma_semestre`)"+
		    "REFERENCES "+DB_NAME+".`turma` (`numero` , `disciplina_cod` , `semestre`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE CASCADE,"+
		  "CONSTRAINT `fk_projeto_professor1`"+
		    "FOREIGN KEY (`professor_cpf`)"+
		    "REFERENCES "+DB_NAME+".`professor` (`cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";

	private static final String EDITAL = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`edital` ("+
		  "`cod` INT NOT NULL AUTO_INCREMENT,"+
		  "`informacoes_adicionais` TEXT NULL,"+
		  "`n_vagas` INT(2) NOT NULL,"+
		  "`data_inicio` DATETIME NOT NULL,"+
		  "`data_fim` DATETIME NOT NULL,"+
		  "`documentos_necessarios` TEXT NOT NULL,"+
		  "`projeto_cod` INT NOT NULL,"+
		  "PRIMARY KEY (`cod`),"+
		  "INDEX `fk_edital_projeto1_idx` (`projeto_cod` ASC),"+
		  "CONSTRAINT `fk_edital_projeto1`"+
		    "FOREIGN KEY (`projeto_cod`)"+
		    "REFERENCES "+DB_NAME+".`projeto` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	
	private static final String BOLSA = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`bolsa` ("+
		  "`cod` INT NOT NULL AUTO_INCREMENT,"+
		  "`data_inicio` DATE NOT NULL,"+
		  "`data_fim` DATE NOT NULL,"+
		  "`valor` DECIMAL(4,2) NOT NULL,"+
		  "`edital_cod` INT NOT NULL,"+
		  "PRIMARY KEY (`cod`),"+
		  "INDEX `fk_bolsa_edital1_idx` (`edital_cod` ASC),"+
		  "CONSTRAINT `fk_bolsa_edital1`"+
		    "FOREIGN KEY (`edital_cod`)"+
		    "REFERENCES "+DB_NAME+".`edital` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";

	private static final String RELATORIO =
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`relatorio` ("+
		  "`cod` INT NOT NULL AUTO_INCREMENT,"+
		  "`texto` TEXT NULL,"+
		  "`data_criacao` DATE NOT NULL,"+
		  "PRIMARY KEY (`cod`))"+
		"ENGINE = InnoDB;";
	
	private static final String RELATORIO_MONITOR = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`r_monitor` ("+
		  "`relatorio_cod` INT NOT NULL,"+
		  "`data_aceito_comissao` DATE NULL,"+
		  "`ata_aprovacao` TEXT NULL,"+
		  "PRIMARY KEY (`relatorio_cod`),"+
		  "CONSTRAINT `fk_r_monitor_relatorio1`"+
		    "FOREIGN KEY (`relatorio_cod`)"+
		    "REFERENCES "+DB_NAME+".`relatorio` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	
	private static final String RELATORIO_PROFESSOR = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`r_professor` ("+
		  "`relatorio_cod` INT NOT NULL,"+
		  "`nota_conceito` DECIMAL(2,2) NOT NULL,"+
		  "PRIMARY KEY (`relatorio_cod`),"+
		  "CONSTRAINT `fk_r_professor_relatorio1`"+
		    "FOREIGN KEY (`relatorio_cod`)"+
		    "REFERENCES "+DB_NAME+".`relatorio` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";

	private static final String ATIVIDADE = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`atividade` ("+
		  "`cod` INT NOT NULL AUTO_INCREMENT,"+
		  "`descricao` TEXT NOT NULL,"+
		  "`data_inicial` DATE NOT NULL,"+
		  "`data_final` DATE NULL,"+
		  "`projeto_cod` INT NOT NULL,"+
		  "`r_monitor_relatorio_cod` INT NULL,"+
		  "`r_professor_relatorio_cod` INT NULL,"+
		  "PRIMARY KEY (`cod`),"+
		  "INDEX `fk_atividade_projeto1_idx` (`projeto_cod` ASC),"+
		  "INDEX `fk_atividade_r_monitor1_idx` (`r_monitor_relatorio_cod` ASC),"+
		  "INDEX `fk_atividade_r_professor1_idx` (`r_professor_relatorio_cod` ASC),"+
		  "CONSTRAINT `fk_atividade_projeto1`"+
		    "FOREIGN KEY (`projeto_cod`)"+
		    "REFERENCES "+DB_NAME+".`projeto` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION,"+
		  "CONSTRAINT `fk_atividade_r_monitor1`"+
		    "FOREIGN KEY (`r_monitor_relatorio_cod`)"+
		    "REFERENCES "+DB_NAME+".`r_monitor` (`relatorio_cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION,"+
		  "CONSTRAINT `fk_atividade_r_professor1`"+
		    "FOREIGN KEY (`r_professor_relatorio_cod`)"+
		    "REFERENCES "+DB_NAME+".`r_professor` (`relatorio_cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	
	private static final String TURMA_LECIONADA_PROFESSOR = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`turma_lecionada_professor` ("+
		  "`turma_numero` VARCHAR(6) NOT NULL,"+
		  "`professor_cpf` VARCHAR(11) NOT NULL,"+
		  "`turma_disciplina_cod` VARCHAR(10) NOT NULL,"+
		  "`turma_semestre` VARCHAR(6) NOT NULL,"+
		  "PRIMARY KEY (`professor_cpf`, `turma_numero`, `turma_disciplina_cod`, `turma_semestre`),"+
		  "INDEX `fk_turma_has_professor_professor1_idx` (`professor_cpf` ASC),"+
		  "INDEX `fk_turma_has_professor_turma1_idx` (`turma_numero` ASC, `turma_disciplina_cod` ASC, `turma_semestre` ASC),"+
		  "CONSTRAINT `fk_turma_has_professor_turma1`"+
		    "FOREIGN KEY (`turma_numero` , `turma_disciplina_cod` , `turma_semestre`)"+
		    "REFERENCES "+DB_NAME+".`turma` (`numero` , `disciplina_cod` , `semestre`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION,"+
		  "CONSTRAINT `fk_turma_has_professor_professor1`"+
		    "FOREIGN KEY (`professor_cpf`)"+
		    "REFERENCES "+DB_NAME+".`professor` (`cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	
	private static final String MONITOR = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`monitor` ("+
		  "`aluno_cpf` VARCHAR(11) NOT NULL,"+
		  "PRIMARY KEY (`aluno_cpf`),"+
		  "CONSTRAINT `fk_monitor_aluno1`"+
		    "FOREIGN KEY (`aluno_cpf`)"+
		    "REFERENCES "+DB_NAME+".`aluno` (`cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	
	private static final String PROJETO_POSSUI_MONITOR = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`projeto_possui_monitor` ("+
		  "`projeto_cod` INT NOT NULL,"+
		  "`monitor_aluno_cpf` VARCHAR(11) NOT NULL,"+
		  "PRIMARY KEY (`projeto_cod`, `monitor_aluno_cpf`),"+
		  "INDEX `fk_projeto_has_monitor_monitor1_idx` (`monitor_aluno_cpf` ASC),"+
		  "INDEX `fk_projeto_has_monitor_projeto1_idx` (`projeto_cod` ASC),"+
		  "CONSTRAINT `fk_projeto_has_monitor_projeto1`"+
		    "FOREIGN KEY (`projeto_cod`)"+
		    "REFERENCES "+DB_NAME+".`projeto` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION,"+
		  "CONSTRAINT `fk_projeto_has_monitor_monitor1`"+
		    "FOREIGN KEY (`monitor_aluno_cpf`)"+
		    "REFERENCES "+DB_NAME+".`monitor` (`aluno_cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";

	private static final String VOLUNTARIO = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`voluntario` ("+
		  "`monitor_aluno_cpf` VARCHAR(11) NOT NULL,"+
		  "PRIMARY KEY (`monitor_aluno_cpf`),"+
		  "CONSTRAINT `fk_voluntario_monitor1`"+
		    "FOREIGN KEY (`monitor_aluno_cpf`)"+
		    "REFERENCES "+DB_NAME+".`monitor` (`aluno_cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	
	private static final String BOLSISTA = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`bolsista` ("+
		  "`monitor_aluno_cpf` VARCHAR(11) NOT NULL,"+
		  "`conta_corrente` VARCHAR(12) NOT NULL,"+
		  "`agencia` VARCHAR(12) NOT NULL,"+
		  "PRIMARY KEY (`monitor_aluno_cpf`),"+
		  "CONSTRAINT `fk_bolsista_monitor1`"+
		    "FOREIGN KEY (`monitor_aluno_cpf`)"+
		    "REFERENCES "+DB_NAME+".`monitor` (`aluno_cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	
	private static final String BOLSA_PERTENCE_BOLSISTA = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`bolsa_pertence_bolsista` ("+
		  "`bolsa_cod` INT NOT NULL,"+
		  "`bolsista_monitor_aluno_cpf` VARCHAR(11) NOT NULL,"+
		  "PRIMARY KEY (`bolsa_cod`, `bolsista_monitor_aluno_cpf`),"+
		  "INDEX `fk_bolsa_has_bolsista_bolsista1_idx` (`bolsista_monitor_aluno_cpf` ASC),"+
		  "INDEX `fk_bolsa_has_bolsista_bolsa1_idx` (`bolsa_cod` ASC),"+
		  "CONSTRAINT `fk_bolsa_has_bolsista_bolsa1`"+
		    "FOREIGN KEY (`bolsa_cod`)"+
		    "REFERENCES "+DB_NAME+".`bolsa` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION,"+
		  "CONSTRAINT `fk_bolsa_has_bolsista_bolsista1`"+
		   "FOREIGN KEY (`bolsista_monitor_aluno_cpf`)"+
		    "REFERENCES "+DB_NAME+".`bolsista` (`monitor_aluno_cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";

	private static final String ALUNO_FAZ_INSCRICAO = 
		"CREATE TABLE IF NOT EXISTS "+DB_NAME+".`aluno_faz_inscricao` ("+
		  "`edital_cod` INT NOT NULL,"+
		  "`aluno_cpf` VARCHAR(11) NOT NULL,"+
		  "`data_inscricao` DATETIME NOT NULL,"+
		  "`nota_processo_seletivo` DECIMAL(2,2) NULL,"+
		  "`numero` INT UNSIGNED NOT NULL AUTO_INCREMENT,"+
		  "PRIMARY KEY (`edital_cod`, `aluno_cpf`),"+
		  "INDEX `fk_edital_has_aluno_aluno1_idx` (`aluno_cpf` ASC),"+
		  "INDEX `fk_edital_has_aluno_edital1_idx` (`edital_cod` ASC),"+
		  "INDEX `edital_has_aluno_numero` USING BTREE (`numero` ASC),"+
		  "CONSTRAINT `fk_edital_has_aluno_edital1`"+
		    "FOREIGN KEY (`edital_cod`)"+
		    "REFERENCES "+DB_NAME+".`edital` (`cod`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION,"+
		  "CONSTRAINT `fk_edital_has_aluno_aluno1`"+
		    "FOREIGN KEY (`aluno_cpf`)"+
		    "REFERENCES "+DB_NAME+".`aluno` (`cpf`)"+
		    "ON DELETE NO ACTION\n"+
		    "ON UPDATE NO ACTION)"+
		"ENGINE = InnoDB;";
	

	private DB(){
		
		makeConnection();
		createDB();
		
	}
	

	private static void makeConnection(){
		try{
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(url, user, password);
			
			
		 } catch (Exception e) {
		      System.out.println("err in get connection: class DB.java");
		 }finally{
			 
		 }
				
	}
	
	
	private static void createDB(){
			
		Statement statement;
		try {
			statement = connection.createStatement();
			
			statement.addBatch("CREATE DATABASE IF NOT EXISTS "+ DB_NAME);
			statement.addBatch(DEPARTAMENTO);
			statement.addBatch(DISCIPLINA);
			statement.addBatch(ALUNO);
			//statement.addBatch(PROFESSOR_TIPO);
			statement.addBatch(PROFESSOR);
			statement.addBatch(TURMA);
			statement.addBatch(PROJETO);
			statement.addBatch(EDITAL);
			statement.addBatch(BOLSA);
			statement.addBatch(RELATORIO);
			statement.addBatch(RELATORIO_MONITOR);
			statement.addBatch(RELATORIO_PROFESSOR);
			statement.addBatch(ATIVIDADE);
			statement.addBatch(TURMA_LECIONADA_PROFESSOR);
			statement.addBatch(MONITOR);
			statement.addBatch(PROJETO_POSSUI_MONITOR);
			statement.addBatch(VOLUNTARIO);
			statement.addBatch(BOLSISTA);
			statement.addBatch(BOLSA_PERTENCE_BOLSISTA);
			statement.addBatch(ALUNO_FAZ_INSCRICAO);
			
			statement.executeBatch();
			statement.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static Connection getConnectionDB() throws Exception{
		
		if(db == null)
			db = new DB();
		
		return connection;
	}	


	public static String getDbName() {
		return DB_NAME;
	}
	
	
	

}

