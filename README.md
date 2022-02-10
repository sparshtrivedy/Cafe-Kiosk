# Cafe Kiosk

## A self-checkout system for ordering coffee

The pandemic has emphasised the importance of identifying and minimising unnecessary human contact. Checkout counters
at grocery stores, fast food joints etc. were identified as a potential risks for spreading the disease. In order to
minimise human interaction at these checkout points, self-checkout kiosks started coming up.

While I saw a lot of these kiosks at popular grocery stores, I hadn't seen one at any cafe. Being a coffee enthusiast,
I felt that the introduction of a similar kiosk at cafes would be very beneficial. This gave me the idea of designing a
self-checkout application for cafes. I believe this application is very useful and relevant considering the current
situation, and would certainly be helpful in minimizing the points of contact in such cafes.

This application would be used by customers who wish to place an order at a cafe. The customers would be given a series
of options at each stage of placing their order. It is designed to take a complete order, and is no different from a
real barista in performing the function of taking an order.

Our application is designed to accomplish the following tasks:
- Add a drink to the cart
- Remove a drink from the cart
- Review cart
- Place order and generate bill
- Save the order(s)
- Load a previously placed order(s)

### Phase 4: Task 2

Fri Nov 19 21:46:15 PST 2021
Order added

Fri Nov 19 21:46:24 PST 2021
Order added

Fri Nov 19 21:46:39 PST 2021
Order removed

Fri Nov 19 21:46:40 PST 2021
Order added

### Phase 4: Task 3

Design Changes:

- I would make MyFrame a private class within the KioskGUI class, since it is not referenced in any other class.
- The KioskGUI has become unnecessarily long which interferes with the readability of the class. The class cass can
be easily fixed by clearing a package called Tools in the ui package. The Tools package would have a Tool abstract class
and a class for each of the function the program performs. The abstract class would set up the default behaviour for
each of the buttons, and abstract methods for the action listener and for creating the button. this avoids a lot of code
replication and makes the KioskGUI much shorter. The KioskGUI class would then have a class which adds the individual
tools to the placeOrderWindow. 