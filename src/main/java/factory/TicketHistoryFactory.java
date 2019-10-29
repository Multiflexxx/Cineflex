package factory;

import exception.EmptyResultSetException;
import exception.FailedObjectCreationException;
import exception.RequiredFactoryFailedException;
import exception.ResultSetIsNullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import oo.BuchungsStornierung;
import oo.Buchungsbeleg;
import oo.ReservierungsStornierung;
import oo.Reservierungsbeleg;
import oo.Sitz;
import oo.TicketHistory;

public class TicketHistoryFactory {
    public static TicketHistory[] getTicketHistoryByKID(int KID)
            throws RequiredFactoryFailedException {
        TicketHistory[] ticketHistories = null;
        ArrayList<TicketHistory> history = new ArrayList<TicketHistory>();

        Buchungsbeleg[] buchungsbelege = null;
        Reservierungsbeleg[] reservierungsbelege = null;

        buchungsbelege = BuchungsFactory.getBuchungsbelegeByKID(KID);
        reservierungsbelege = ReservierungsFactory.getReservierungsbelegByKID(KID);

        int length = (buchungsbelege == null ? 0 : buchungsbelege.length) + (reservierungsbelege == null ? 0 : reservierungsbelege.length);

        if (length < 1) {
            return null;
        }

        ticketHistories = new TicketHistory[length];

        // Add Buchungen to TicketHistory
        if (buchungsbelege != null) {
            for (int i = 0; i < buchungsbelege.length; i++) {
                // Get Stornierung (if there is one)
                BuchungsStornierung buchungsStornierung = null;
                try {
                    buchungsStornierung = BuchungsStornierungFactory
                            .getBuchungsStornierungByBNR(buchungsbelege[i].getBelegID());
                } catch (ResultSetIsNullException | FailedObjectCreationException e) {
                    e.printStackTrace();
                    throw new RequiredFactoryFailedException();
                } catch (EmptyResultSetException e) {
                    // Buchung not cancelled
                }

                // Get seats for Buchung
                Sitz[] sitze = null;
                sitze = SitzFactory.getSitzeByBNR(buchungsbelege[i].getBelegID());

                if (sitze == null) {
                    throw new RequiredFactoryFailedException();
                }

                ticketHistories[i] = new TicketHistory(buchungsbelege[i], sitze,
                        buchungsStornierung);
            }
        }

        // Add Reservierungen to ticketHistory
        if (reservierungsbelege != null) {
            for (int i = 0; i < reservierungsbelege.length; i++) {
                // Get Stornierung for Reservierung (if there is one)
                ReservierungsStornierung reservierungsStornierung = null;
                try {
                    reservierungsStornierung = ReservierungsStornierungFactory
                            .getReservierungsStornierungByRNR(reservierungsbelege[i].getBelegID());
                } catch (ResultSetIsNullException | FailedObjectCreationException e) {
                    e.printStackTrace();
                    throw new RequiredFactoryFailedException();
                } catch (EmptyResultSetException e) {
                    // Reservierung Cancelled
                }

                // Get Seats for Reservierung
                Sitz[] sitze = null;
                sitze = SitzFactory.getSitzeByRNR(reservierungsbelege[i].getBelegID());

                if (sitze == null) {
                    throw new RequiredFactoryFailedException();
                }

                ticketHistories[buchungsbelege.length + i] = new TicketHistory(
                        reservierungsbelege[i], sitze, reservierungsStornierung);
            }
        }

        for (int i = 0; i < ticketHistories.length; i++) {
            history.add(ticketHistories[i]);
        }
        Collections.sort(history);

        Iterator<TicketHistory> iterator = history.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            TicketHistory currentTicket = iterator.next();
            ticketHistories[counter] = currentTicket;
            counter++;
        }

        return ticketHistories;
    }

}
