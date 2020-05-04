package br.com.appsam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import util.DbUtil;
//a classe DAO tem como funcao: Criar metodos que faz a conexao. 
//Faz a conversao de objeto para o modelo do banco, do modelo do banco para objetos

//toda classe que tiver na model, vai ter uma referente na DAO
//é a DAO que vai salvar, buscar no banco
public class UserDao {
	//unico atributo connection, pq vai fazer o meio de campo entre o OO e BD
    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }
    //1o metodo de add user...do tipo void pq nao retorna nada e simplesmente finaliza
    public void addUser(User user) {//recebendo um usuario user do tipo User(classe) 
        try {
            PreparedStatement preparedStatement = connection//objeto que vai preparar o script de insercao no BD
                    .prepareStatement("insert into users(firstname,lastname,dob,email) values (?, ?, ?, ? )");
            //Parametros comeca com 1
            preparedStatement.setString(1, user.getFirstName());//setString - formato que vou inserir
            preparedStatement.setString(2, user.getLastName());//setString - formato que vou inserir
            preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));//transforma para o tipo Date...setDate - formato que vou inserir
            preparedStatement.setString(4, user.getEmail());//setString - formato que vou inserir
            preparedStatement.executeUpdate();//executeUpdate - preparar para executar esse comando. Executa a query 

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  //2o metodo de deletar user...do tipo void pq nao retorna nada
    public void deleteUser(int userId) {//userId - somente precisa saber do ID do usuario do tipo int
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where userid=?");
            // Parametros comeca com 1
            preparedStatement.setInt(1, userId);//passa o int para o ponto de interrogacao
            preparedStatement.executeUpdate();//vai executar o delete somente o numero que passou como parametro

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //3o metodo de atualizar user...do tipo void pq nao retorna nada
    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?" +
                            "where userid=?");//userid=?so vai atualizar dados deste usuario em especifico
            //Parametros comeca com 1
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getUserid());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //4o metodo de pegar todos os usuarios
    public List<User> getAllUsers() {//()nao recebe nenhum parametro. O tipo de retorno é List<user>
    	//vai retornar uma lista do tipo de usuarios users
        List<User> listaDeUsuario = new ArrayList<User>();
        try {
            Statement stmt = connection.createStatement();//faz o statement para criar a conexao
            ResultSet rs = stmt.executeQuery("select * from users");//busca todas as colunas de todos os usuarios
            //qndo a query retornar, vai quardar o resultado dentro da variavel rs q é do tipo ResultSet
            //ResultSet - e como se criasse uma tabela do BD e salva no no ResultSet
            while (rs.next()) {//while e pra percorrer cada linha da tabela
                User user = new User();//cria o user, e pra cada user, cria as variaveis abaixo
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                listaDeUsuario.add(user);//listaDeUsuario-vai adicionar no objeto user os dados que vieram do banco
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeUsuario;//qndo retornar, é a lista com todos os usuarios que veio do (select * from users)
    }
  //5o metodo de ver se usuario existe no banco e se existe, retornar ele
    public User getUserById(int userId) {//vai retornar um unico usuario
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();//vai executar a query

            if (rs.next()) {//se existir vai pegar user e fazer abaixo
            	//se usuario exister, insere esses valores e retorna usuario (user)
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;//retorna user
    }
}