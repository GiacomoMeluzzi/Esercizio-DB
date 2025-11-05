package lepackage.enterprise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Driver {
	public static final String MESSAGGIO = "0) Esci\n1) Mostra database\n2) Mostra da ID\n3) Aggiungi\n4) Elimina da ID\n5) Salva";

	public static void main(String[] args) {

		Connection connection = ConnectionSingleton.getInstance().getCon();
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
		PreparedStatement state;
		PreparedStatement statement;
		ResultSet result;
		CarroController controller = CarroController.getInstance();

		int input;
		try {
			while (true) {
				System.out.println(MESSAGGIO);
				input = ScannerSingleton.getInt();
				switch (input) {
				case 1:
					try {
						statement = connection.prepareStatement("select * from carriarmati");
						result = statement.executeQuery();
						ResultSetMetaData meta = result.getMetaData();
						
						String data = "";
						String id = meta.getColumnLabel(1);
						String nome = meta.getColumnLabel(2);
						String peso = meta.getColumnLabel(3);
						System.out.println("-----------------------------------------------");
						System.out.println("|" + id + "    | " + nome + "                      | " + peso + "  |");
						System.out.println("-----------------------------------------------");
						while (result.next()) {
							for (int j = 1; j <= 3; j++) {
								data = result.getNString(j);
								System.out.print("|" + data + "     ");
							}
							System.out.print("|\n");
						}			
						System.out.println("-----------------------------------------------");
						
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					break;
				case 3:
					String[] dati = controller.insertDb();
					try {
						statement = connection.prepareStatement("select id from carriarmati order by id desc");
						result = statement.executeQuery();
						state = connection
								.prepareStatement("insert into carriarmati (id, modello, peso)" + " values (?, ?, ?)");
						if (result.next()) {
							int idN = Integer.parseInt(result.getString(1)) + 1;
							String id = String.valueOf(idN);
							state.setString(1, id);
						} else {
							state.setString(1, "0");
						}
						state.setString(2, dati[0]);
						state.setString(3, dati[1]);
						state.execute();
						System.out.println("Inserimento effettuato con successo.");
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					String id = controller.delete();
					try {
						statement = connection.prepareStatement("select * from carriarmati where id = ?");
						statement.setString(1, id);
						result = statement.executeQuery();
						if (result.next()) {
							state = connection.prepareStatement("delete from carriarmati where id = ?");
							state.setString(1, id);
							state.execute();
							System.out.println("Eliminazione effettuata con successo!");
						} else {
							System.out.println("Carro non presente nell'elenco.");
						}
					} catch (SQLException e) {

					}
					;
					break;
				case 5:
					try {
						state = connection.prepareStatement("commit");
						state.execute();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 0:
					try {
						connection.close();
						System.out.println("Chiusura in corso...");
						System.exit(0);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				default:
				}
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			try {
				connection.close();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
}
