This project represents a scala fullstack app using:
* **Laminar** as a frontend
* **Scala 3** as a backend

### Prerequisites
- SBT
- JDK 11
- Node js v16+

### Local development setup

Step 1: Running backend:
```shell
sbt
~backend/reStart
```

Step 2: Installing NPM dependencies:
```shell
cd frontend
npm ci
```

Step 3: Building frontend:
```shell
sbt
~frontend/fastLinkJS
```

Step 4: Running frontend:
```shell
cd frontend
npm run dev
```