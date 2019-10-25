package helper;

import exception.EmptyResultSetException;
import exception.InvalidInputValueException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;
import factory.PreisänderungsFactory;
import factory.SitzFactory;
import javax.servlet.http.HttpSession;
import oo.Preisänderung;
import oo.Sitz;

public class TempBuchungHandler {
    public static void addTempBuchungToSession(HttpSession session, String sitzIDs, String preisVerIDs)
				throws InvalidInputValueException, RequiredFactoryFailedException {
    		if(sitzIDs == "" | preisVerIDs == "") {
    				throw new InvalidInputValueException();
				}

    		if(!(sitzIDs.matches(".*\\d.*") & !sitzIDs.matches(".*\\d.*"))) {
    				throw new InvalidInputValueException();
				}

        int[] intSeatIDs = ArrayBuilder.stringToIntArray(sitzIDs);
    		int[] intPreisVerIDs = ArrayBuilder.stringToIntArray(preisVerIDs);

    		if((intSeatIDs.length < 1 | intPreisVerIDs.length < 1) || intSeatIDs.length == intPreisVerIDs.length) {
    				throw new InvalidInputValueException();
				}
				Sitz[] sitze = new Sitz[intSeatIDs.length];
    		for(int i = 0; i < sitze.length; i++) {
    				sitze[i] = SitzFactory.getSitzById(intSeatIDs[i]);
				}

    		Preisänderung[] preisänderungen = new Preisänderung[intPreisVerIDs.length];
    		for(int i = 0; i < preisänderungen.length; i++) {
						try {
								preisänderungen[i] = PreisänderungsFactory.getPreisänderungById(intPreisVerIDs[i]);
						} catch (ResultSetIsNullException e) {
								e.printStackTrace();
								throw new RequiredFactoryFailedException();
						} catch (EmptyResultSetException e) {
								e.printStackTrace();
								throw new RequiredFactoryFailedException();
						}
				}

    		session.setAttribute("temp_sitze", sitze);
    		session.setAttribute("temp_preisänderungen", preisänderungen);
    }
}
