package br.com.genericdelivery.util.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import br.com.genericdelivery.util.EncryptionUtil;

public class StringEncrypted implements UserType {
	public static final String TYPE = "stringCriptografadaType";

	@Override
	public int[] sqlTypes() {
		return new int[] { StringType.INSTANCE.sqlType() };
	}

	@Override
	public Class<String> returnedClass() {
		return String.class;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] campos,
			SessionImplementor sessionImpl, Object o)
			throws HibernateException, SQLException {
		String valorCampo = rs.getString(campos[0]);
		if (valorCampo != null) {
			return EncryptionUtil.descriptografa(valorCampo);
		} else {
			return null;
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object valor, int index,
			SessionImplementor sessionImpl) throws HibernateException,
			SQLException {
		String valorString = (String) valor;
		if (valorString != null) {
			st.setString(index, EncryptionUtil.criptografa(valorString));
		} else {
			st.setNull(index, StringType.INSTANCE.sqlType());
		}
	}

	/* implementações "padrão" */

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == null)
			return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

}
