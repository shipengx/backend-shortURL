package org.shipeng.backend_shortURL.dao;

import java.util.List;

import org.shipeng.backend_shortURL.model.*;

public interface urlDAO {

	public long saveURL(final URL url);
	public long updateURL(URL url);
	public void delete(int urlId);
	public URL get(long urlId);
	public List<URL> list();
	
}
