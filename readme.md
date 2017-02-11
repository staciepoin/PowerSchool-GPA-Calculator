# PowerSchool GPA-Calculator

My school uses PowerSchool as their online gradebook, but to relieve stress, they've made the GPA invisible. It didn't really relieve stress as much as it did in causing confusion in how to calculate your GPA. So I built this. It's a small app that takes in a user's credentials, scrapes their gradebook data, calculates their GPA, and displays it. Your one stop shop to get happy/sad.


### Setup

I'm working on hosting it online to be run from a website, but for now you can download it from /out/artifacts/PowerSchool_ASD_jar/

### Open Source

This project is definitely open source, and I invite others in schools that use PowerSchool to implement it for them. The dependent libraries are included in the project files.

* Replace the PowerSchool Links in the PSLogin and Calculator classes
* Tweak the JSoup fetching to fit your web page's layout.
* Change up the design!

### Development

A few things are still a li'l finicky: the table tends to add classes instead of replacing them :/

License
----

MIT


