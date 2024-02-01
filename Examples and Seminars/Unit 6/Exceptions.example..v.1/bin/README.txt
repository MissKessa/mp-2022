This version is prepared to react to problems in that way:

	- The file does not exist
		Shows a message to the user indicating to execute the program again 
		with a valid file name and terminates.
		
	- The file is empty
		Shows a message to the user indicating to review the content of the file
		and terminates.

	- The file has blank lines
	- Some line has more or less fields than expected
	- Some fields has wrong format, ex. numeric fields with letters, wrong dates, etc.
		Logs a message indicating what the wrong line is, but the execution 
		continues.
		
	- The program runs out of memory
	- There is a hardware error: memory, processor, etc.
	- Somebody unplugs the USB stick with the file we are processing while reading it
	- The program has bugs
		Shows a message to user indicating "there has been some technical 
		issue that must be solved by the technical staff" and logs the full
		trace of the exception onto a log file.  
	