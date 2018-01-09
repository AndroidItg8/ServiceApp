package itg8.com.serviceapp.common_method;


import android.support.v4.app.RemoteInput;

import java.util.ArrayList;
import java.util.List;

import itg8.com.serviceapp.showcase.model.ShowCaseModel;
import itg8.com.serviceapp.ticket.model.TicketModel;

/**
 * Created by Android itg 8 on 9/20/2017.
 */

public class FilterUtility {

    private FilterBuilder filterBuilder;

    public FilterUtility(FilterBuilder filterBuilder) {

        this.filterBuilder = filterBuilder;
    }

    public List<TicketModel> getFilteredList()
    {
        return filterBuilder.ticketModelList;
    }



    public static class FilterBuilder{
        private List<TicketModel> ticketModelList;


        public FilterBuilder createBuilder(List<TicketModel> tblStateList)
        {
            this.ticketModelList = new ArrayList<>();
            this.ticketModelList.addAll(tblStateList);
            return this;
        }



        public FilterBuilder setFilter()
        {
            List<TicketModel> tempOpenTicket = new ArrayList<>();

            for (TicketModel ticket: ticketModelList) {
                 if(ticket.getStatus()== null||ticket.getStatus().equals(CommonMethod.STATUS_OPEN));
                 {
                     tempOpenTicket.add(ticket);

                 }

            }

            ticketModelList.clear();
            ticketModelList.addAll(tempOpenTicket);
            return this;
        }
   public FilterBuilder setFilterClose()
        {

            List<TicketModel> tempCloseTicket = new ArrayList<>();
            for (TicketModel ticket: ticketModelList) {

                 if(ticket.getStatus().equals(CommonMethod.STATUS_CLOSE))
                 {
                     tempCloseTicket.add(ticket);
                 }else
                 {
                     ticketModelList.clear();
                      ticketModelList  = new ArrayList<TicketModel>();
                     return this ;
                 }
            }
//
            ticketModelList.clear();
            ticketModelList.addAll(tempCloseTicket);
            return this;
        } public FilterBuilder setFilterAssign()
        {

            List<TicketModel> tempAssignTicket = new ArrayList<>();
            for (TicketModel ticket: ticketModelList) {

                 if(ticket.getStatus().equals(CommonMethod.STATUS_CLOSE))
                 {
                     tempAssignTicket.add(ticket);
                     ticketModelList.addAll(tempAssignTicket);
                 }
                 else
                 {
                     ticketModelList.clear();
                     ticketModelList  = new ArrayList<TicketModel>();
                     return this ;
                 }
            }
//

            return this;
        }



        public FilterUtility build()
        {
             return new FilterUtility(this);
        }


    }
}
