This is version is a variation on the version 1.

	This reacts by stopping the execution if ANY type of error is found while
	processing the file. Error lines are not ignored and the execution 
	is stopped.


	- The file has blank lines
	- Some line has more or less fields than expected
	- Some fields has wrong format, ex. numeric fields with letters, wrong dates, etc.
		Shows a message to the user indicating to review the content of the file
		and terminates.
		
	- The rest of errors are handled equally
	
	In order to simplify the proccessFile method header in its throws clause the
exception hierarchy is modified so that LineFormatException is a child 
of FileFormatException

public double processFile(String fName) throws FileFormatException, IOException, LineFormatException {

	... remains as ...
	 
public double processFile(String fName) throws FileFormatException, IOException {
	
	