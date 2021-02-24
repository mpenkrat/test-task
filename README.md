
REST API that provides an interface for hotels to enter the numbers of Premium and Economy rooms that are available for the night and then tells them immediately how many rooms of each category will be occupied and how much money they will make in total. Potential guests are represented by an array of numbers that is their willingness to pay for the night.

### Clone Instruction

To clone repository with all submodules use following command
```
git clone https://github.com/mpenkrat/test-task.git
```

### Required installations
```
JDK 11+, Maven 3.6+
```

### Build Instruction

To build module with all dependencies use following command
```
mvn clean install
```

### Tests
```
mvn test
```

### Run locally
```
mvn spring-boot:run
```

Run request via http client (i.e. CURL)
```
curl -X GET -H "Cache-Control: no-cache" "http://localhost:8080/api/v1/rooms/occupancy?economy=3&premium=3"
```

