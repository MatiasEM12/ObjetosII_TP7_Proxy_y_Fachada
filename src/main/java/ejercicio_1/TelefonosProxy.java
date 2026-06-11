package ejercicio_1;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TelefonosProxy implements Set<Telefono> {

    private int idPersona;
    private Set<Telefono> telefonos;

    public TelefonosProxy(int idPersona) {
        this.idPersona = idPersona;
    }

    private void cargarSiEsNecesario() {

        if(telefonos != null)
            return;

        telefonos = new HashSet<>();

        try(Connection conn = DriverManager.getConnection("jdbc:derby:proxy_personas");
            PreparedStatement st = conn.prepareStatement("SELECT numero FROM telefonos WHERE idPersona = ?")) {

                st.setInt(1, idPersona);

                ResultSet rs = st.executeQuery();

                while(rs.next()) {
                    telefonos.add(
                            new Telefono(rs.getString("numero"))
                    );
                }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int size() {
        cargarSiEsNecesario();
        return telefonos.size();
    }

    @Override
    public boolean isEmpty() {
        cargarSiEsNecesario();
        return telefonos.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        cargarSiEsNecesario();
        return telefonos.contains(o);
    }

    @Override
    public Iterator<Telefono> iterator() {
        cargarSiEsNecesario();
        return telefonos.iterator();
    }

    @Override
    public Object[] toArray() {
        cargarSiEsNecesario();
        return telefonos.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        cargarSiEsNecesario();
        return telefonos.toArray(a);
    }

    @Override
    public boolean add(Telefono telefono) {
        cargarSiEsNecesario();
        return telefonos.add(telefono);
    }

    @Override
    public boolean remove(Object o) {
        cargarSiEsNecesario();
        return telefonos.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        cargarSiEsNecesario();
        return telefonos.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Telefono> c) {
        cargarSiEsNecesario();
        return telefonos.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        cargarSiEsNecesario();
        return telefonos.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        cargarSiEsNecesario();
        return telefonos.retainAll(c);
    }

    @Override
    public void clear() {
        cargarSiEsNecesario();
        telefonos.clear();
    }
}
