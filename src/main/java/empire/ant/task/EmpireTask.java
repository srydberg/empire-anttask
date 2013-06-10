package empire.ant.task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;


public class EmpireTask extends Task {

	File sourceFile;
	public void setSourceFile( File sourceFile) {
		this.sourceFile = sourceFile;
	}

	File outputFile;
	public void setOutputFile( File outputFile ) {
		this.outputFile = outputFile;
	}

	@Override
	public void execute() throws BuildException {
		Project prj = getProject();

		log( "Compiling '" + sourceFile.getAbsolutePath() );

		try {
			InputStream input = new BufferedInputStream( new FileInputStream( sourceFile ) );
			OutputStream output = new BufferedOutputStream( new FileOutputStream( outputFile ) );

			empire.lang.Compiler compiler = new empire.lang.Compiler(input, output, prj.getProperties());
			compiler.compile();
		} catch (IOException e) {
			log( e.getMessage(), Project.MSG_ERR );
			throw new BuildException(e.getMessage(),e);
		}

		 log("Wrote compilation to " + outputFile.getAbsolutePath());
	}
}
