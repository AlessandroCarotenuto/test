package database;

public class DAOException extends Exception
{

	private static final long serialVersionUID = 1L;

	public DAOException(String error) 
	{
		super(error);
	}
}
