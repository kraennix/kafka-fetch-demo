**Steps to reproduce behavior with large record values:**

1. Start a local kafka instance (e.g. start included docker-compose file)
2. Produce multiple events on a topic --> Start application with spring profile `produce, large`
3. Start application with spring profile `consume, large` and consume events

You should see that after executing step 3 it starts consuming the kafka messages, but it stocks for 10 seconds after
consuming some.

**Steps to reproduce behavior with small record values:**

1. Start a local kafka instance (e.g. start included docker-compose file)
2. Produce multiple events on a topic --> Start application with spring profile `produce, small`
3. Start application with spring profile `consume, small` and consume events

In this case you should see that everything is working fine and all messages are consumed immediately.