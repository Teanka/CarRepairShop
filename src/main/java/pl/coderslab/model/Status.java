package pl.coderslab.model;

public enum Status {
        ACCEPTED("Przyjety"),
        APPROVED("Zatwierdzone_koszty_naprawy"),
        IN_PROGRESS("W_naprawie"),
        READY("Gotowy_do_odbioru"),
        REJECTED("Rezygnacja");

        private String action;

        // getter method
        public String getAction()
        {
            return this.action;
        }

        // enum constructor - cannot be public or protected
        private Status(String action)
        {
            this.action = action;
        }

}
//public class EnumConstructorExample
//{
//    public static void main(String args[])
//    {
//        // let's print name of each enum and there action
//        // - Enum values() examples
//        TrafficSignal[] signals = TrafficSignal.values();
//
//        for (TrafficSignal signal : signals)
//        {
//            // use getter method to get the value
//            System.out.println("name : " + signal.name() +
//                    " action: " + signal.getAction() );
//        }
//    }
//}

