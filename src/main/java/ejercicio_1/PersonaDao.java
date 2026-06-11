package ejercicio_1;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao {
    private Connection obtenerConexion() {
        try {
            return DriverManager.getConnection("jdbc:derby:proxy_personas;create=true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Persona personaPorId(int id) {
        String sql = "select p.nombre,t.numero "
                + "from personas p, telefonos t "
                + "where p.id = t.idpersona and p.id = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Set<Telefono> telefonos = new HashSet<Telefono>();
            String nombrePersona = null;
            while (result.next()) {
                nombrePersona = result.getString(1);
                telefonos.add(new Telefono(result.getString(2)));
            }
            return new Persona(id, nombrePersona, telefonos);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearTablas() {
        try (Connection conn = obtenerConexion();
             Statement st = conn.createStatement()) {

            st.executeUpdate("""
            CREATE TABLE personas (
                id INT PRIMARY KEY,
                nombre VARCHAR(100)
            )
        """);

            st.executeUpdate("""
            CREATE TABLE telefonos (
                id INT PRIMARY KEY
                    GENERATED ALWAYS AS IDENTITY,
                numero VARCHAR(20),
                idPersona INT
            )
        """);

            st.executeUpdate("""
            INSERT INTO personas(id,nombre)
            VALUES (1,'Pepe Argento')
        """);

            st.executeUpdate("""
            INSERT INTO telefonos(numero,idPersona)
            VALUES ('2920-111111',1)
        """);

            st.executeUpdate("""
            INSERT INTO telefonos(numero,idPersona)
            VALUES ('2920-222222',1)
        """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
