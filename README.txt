
How to use
----------

The API classes can be found under com.intuit.sample.api, the implementation classes under com.intuit.sample.impl.

The central unit is the Bookshelf interface, with the BookshelfImpl implementation. Examples and expectations can be found in the corresponding unit tests under /src/test/java.


A brief discussion of the design decisions made in implementing the sample project
----------------------------------------------------------------------------------

Q: What on Earth was I doing with searching?
A: I borrowed the specifications idea from Spring Data. It seemed interesting, and a lot more fun to implement than the alternatives.

Q: What alternatives?
A: I could index by each field using more Maps. This would be a lot more performant than the approach I took, at the cost of memory and flexbility. Or I could have hard coded the search comparisons. This would eliminate the extra memory usage at a cost to performance, development speed, and flexbility. 

Q: Why so many lambdas?
A: Perhaps they were somewhat excessive, but so much more fun.

Q: Why did you split the api and impl? Why have both BookDto and Book?
A: I did this to mimic how it could be implemented as a service.

Q: How would the design change if we were implementing a Library with multiple shelves?
A: Honestly, I would rethink the whole thing. Most functionality would be pulled out the Bookshelf object, into a Library object and it's minions. Bookshelf would become a small piece of data in a large system. Searching should be across the whole library, not shelf by shelf. Same story with other behavior.

Q: How could we operate on the bookshelf via a REST API?
A: If we were so inclined, the bookshelf could be exposed as a resource. In fact, since I was borrowing ideas from Spring Data anyway, we could reimplement it using Spring Data REST in an almost one to one fashion.

