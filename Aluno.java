package av1orientacaodois;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno {

	int matricula;
	String nome;
	int periodo;
	float nota;

	public Aluno() {

	}

	public Aluno(int matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public Aluno(int matricula, String nome, int periodo, float nota) {

		this.matricula = matricula;
		this.nome = nome;
		this.periodo = periodo;
		this.nota = nota;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Connection conectar() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://192.168.202.203:3306/alunos";
			String user = "userbanco";
			String password = "1User#Banco@sql";
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não localizado: " + e.getMessage());
			return null;
		}
	}

	public void inserir() {
		try {

			Connection con = conectar();

			if (con != null) {
				String sql = "INSERT INTO aluno (matricula, nome, periodo, nota) VALUES (?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, this.matricula);
				ps.setString(2, this.nome);
				ps.setInt(3, this.periodo);
				ps.setFloat(4, this.nota);
				ps.executeUpdate();
				ps.close();
				con.close();

				System.out.println("Pessoa inserida no banco de dados com sucesso!");

			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir pessoa no banco de dados!");
		}
	}

	public void alterar() {
		try {

			Connection con = conectar();

			if (con != null) {
				String sql = "UPDATE aluno SET matricula = ?, nome = ?, periodo = ?, nota = ?";

				PreparedStatement ps = con.prepareStatement(sql);

				ps.setInt(1, this.matricula);
				ps.setString(2, this.nome);
				ps.setInt(3, this.periodo);
				ps.setFloat(4, this.nota);

				ps.executeUpdate();
				ps.close();

				con.close();

				System.out.println("Pessoa alterada no banco de dados com sucesso!");

			}
		} catch (SQLException e) {
			System.out.println("Erro ao alterar pessoa no banco de dados!");

		}
	}

	public void excluir() {
		try {

			Connection con = conectar();

			if (con != null) {
				String sql = "DELETE FROM aluno WHERE matricula = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, this.matricula);
				ps.executeUpdate();
				ps.close();
				con.close();
				System.out.println("Pessoa excluída no banco de dados com sucesso!");

			}
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa no banco de dados!");
		}
	}

	public void consultar() {
		try {
			Connection con = conectar();
			if (con != null) {
				String sql = "SELECT * FROM aluno WHERE matricula = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, this.matricula);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					System.out.println("Matricula: " + rs.getInt("matricula"));
					System.out.println("Nome: " + rs.getString("nome"));
					System.out.println("Periodo: " + rs.getInt("periodo"));
					System.out.println("Nota: " + rs.getString("float"));
				} else {
					System.out.println("Pessoa não encontrada!");
				}
				rs.close();
				ps.close();
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa no banco de dados: " + e.getMessage());
		}
	}

	public void mostrartodos() {
		try {
			Connection con = conectar();
			if (con != null) {
				String sql = "SELECT * FROM aluno";
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					System.out.println("Matrícula:" + rs.getInt("matricula"));
					System.out.println("Nome:" + rs.getString("nome"));
					System.out.println("Período:" + rs.getInt("periodo"));
					System.out.println("Nota" + rs.getFloat("nota"));
				}
				rs.close();
				ps.close();
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoas no banco de dados" + e.getMessage());
		}
	}

	//public static void main(String[] args) {

		//Aluno a1 = new Aluno(1111111, "Juan Vitor Mendes", 4, 8.5f);

		//a1.inserir();
		//a1.consultar();
		
		//a1.setMatricula(123);
		//a1.setNome("João");
		//a1.setPeriodo(2);
		//a1.setNota(7.7f);
		
		//a1.mostrartodos();
		
		//a1.excluir();
	}

}
