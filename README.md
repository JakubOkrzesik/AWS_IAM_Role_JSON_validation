## Remitly AWS IAM Role JSON validation
The program provides a validation function to verify the AWS IAM Role policy JSON data
### Usage

#### 1. Compile the Program
You can compile the JsonChecker program using the following command:

```
javac JsonChecker.java
```
Ensure to load the dependencies from the pom.xml file using Maven and you're in the same directory as the JsonChecker.java

#### 2. Run the Program
Running with Command Line Arguments
If you have the JSON file paths as command line arguments, you can run the program like this:

```
java JsonChecker /path/to/your/json/file1.json /path/to/your/json/file2.json
```

Running without Command Line Arguments
If you want to provide the JSON file path interactively:

```
java JsonChecker
```
The program will prompt you to enter the path to the JSON file.

#### 3. Using Provided JAR File
If you prefer, you can directly use the provided JAR file out/artifacts/Remitly_intern_task_jar/Remitly_intern_task.jar to run the program.
```
java -jar ./Remitly_intern_task.jar /path/to/your/json/file.json
```
You can also run the file interactively like in the upper example
