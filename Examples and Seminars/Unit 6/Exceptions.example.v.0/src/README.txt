This is the basic version of a program for computing the total balance
of an account whose records (movements) are in a text file with a line per
movement. 

	Each line has three parts (fields) <date> <description> <amount>. Every 
field is separated by a ';' character.

	The code works if "all goes well". But some issues might occur. What
happens if:

	- The file does not exist?
	- The file is empty?
	- The file has blank lines?
	- Some line has more or less fields than expected?
	- Some fields has wrong format, ex. numeric fields with letters, 
		wrong dates, etc.?
	- The program runs out of memory?
	- There is a hardware error: memory, processor, etc.?
	- Somebody unplugs the USB stick with the file we are processing while reading it?
	- The code has bugs?
	- ...
	
	There are lots of situations that will break the execution. But our code
must be resilient to some of this problems or at least be able to terminate
gracefully.
	