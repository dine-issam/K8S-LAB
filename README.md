# Microservices Architecture with Kubernetes

This project demonstrates a complete microservices architecture deployed on Kubernetes with Spring Cloud components including Eureka service discovery, API Gateway, circuit breakers, and caching mechanisms.

## 🏗️ Architecture Overview

The system consists of the following components:

### Core Services
- **Registry Service (Eureka Server)** - Service discovery and registration
- **API Gateway** - Single entry point for all client requests
- **Patient Service (ms-patient)** - Manages patient data and medical history
- **Prescription Service (ms-ordonnance)** - Handles prescription management
- **Reimbursement Service (ms-remboursement)** - Processes reimbursement claims

### Infrastructure Components
- **MySQL Database** - Persistent storage for patient data
- **H2 Database** - In-memory database for other services
- **NGINX Ingress Controller** - External traffic routing
- **MetalLB** - Load balancer for bare-metal Kubernetes

## 🚀 Features

- **Service Discovery** - Automatic service registration and discovery with Eureka
- **Circuit Breaker** - Fault tolerance using Resilience4j
- **Caching** - Performance optimization with Caffeine cache
- **Load Balancing** - Client-side load balancing with Spring Cloud LoadBalancer
- **Auto Scaling** - Horizontal Pod Autoscaler (HPA) based on CPU utilization
- **Persistent Storage** - PersistentVolumes for data persistence
- **Secrets Management** - Kubernetes secrets for sensitive configuration
- **Ingress** - Path-based routing for external access

## 📋 Prerequisites

- Kubernetes cluster (v1.20+)
- kubectl configured
- Docker registry accessible from cluster
- NGINX Ingress Controller installed
- MetalLB installed (for LoadBalancer services)

## 🛠️ Deployment Instructions

### 1. Clone and Build Applications

```bash
# Navigate to each microservice directory and build
cd MS_APP/ms-patient
./mvnw clean package -DskipTests
docker build -t 192.168.3.1:5000/ms-patient-image .
docker push 192.168.3.1:5000/ms-patient-image

cd ../ms-ordonnance
./mvnw clean package -DskipTests
docker build -t 192.168.3.1:5000/ms-ordonnance-image .
docker push 192.168.3.1:5000/ms-ordonnance-image

cd ../ms-remboursement
./mvnw clean package -DskipTests
docker build -t 192.168.3.1:5000/ms-remboursement-image .
docker push 192.168.3.1:5000/ms-remboursement-image

# Build registry service
cd ../registry
./mvnw clean package -DskipTests
docker build -t 192.168.3.1:5000/registry-image .
docker push 192.168.3.1:5000/registry-image
```

### 2. Deploy Infrastructure Components

```bash
# Deploy MySQL with secrets
kubectl apply -f K8S_Deployment_files/mysql_secret.yml
kubectl apply -f K8S_Deployment_files/mysql-with-secret-depl.yaml

# Deploy MetalLB configuration
kubectl apply -f K8S_Deployment_files/ingress/ip_pool.yml
kubectl apply -f K8S_Deployment_files/ingress/L2Ad.yml
```

### 3. Deploy Core Services

```bash
# Deploy Eureka Registry
kubectl apply -f K8S_Deployment_files/registry-depl.yaml

# Wait for registry to be ready
kubectl wait --for=condition=ready pod -l app=registry-pod --timeout=300s

# Deploy microservices
kubectl apply -f K8S_Deployment_files/ms-patient-deploy.yaml
kubectl apply -f K8S_Deployment_files/ms-patient-mysql-deploy.yaml
kubectl apply -f K8S_Deployment_files/ms-ordonnance-deploy.yaml
kubectl apply -f K8S_Deployment_files/ms-remboursement-deploy.yaml
```

### 4. Configure Ingress and Auto-scaling

```bash
# Deploy ingress for external access
kubectl apply -f K8S_Deployment_files/ingress/ingress.yml

# Deploy HPA for auto-scaling
kubectl apply -f Auto-scaling/nginx-deployment3.yaml
kubectl apply -f Auto-scaling/hpa.yaml
```

## 🔧 Configuration Details

### Service Ports
- **Registry (Eureka)**: 8888
- **Patient Service**: 8081
- **Prescription Service**: 8082
- **Reimbursement Service**: 8083
- **Patient MySQL Service**: 8085

### Database Configuration
- **MySQL**: Persistent storage with secrets management
- **H2**: In-memory databases for ordonnance and remboursement services

### Auto-scaling Configuration
- **Min Replicas**: 2
- **Max Replicas**: 10
- **CPU Target**: 15% utilization
- **Resource Requests**: 100m CPU
- **Resource Limits**: 200m CPU

## 🌐 API Endpoints

### Patient Service
```
GET /api/patient/{id} - Get patient with prescriptions
GET /patients/{id} - Get patient details (Spring Data REST)
```

### Prescription Service
```
GET /api/ordonnance/{id} - Get prescription details
GET /api/ordonnance2/{id} - Get prescription with error handling
GET /ordonnances/search/findOrdonnancesByIdPatient - Find by patient ID
```

### Reimbursement Service
```
GET /remboursements/{id} - Get reimbursement details
```

### External Access (via Ingress)
```
/service-patient/* → Patient Service (8081)
/service-ordonnance/* → Prescription Service (8082)
/service-remboursement/* → Reimbursement Service (8083)
```

## 🔍 Monitoring and Health Checks

### Service Discovery
- Eureka Dashboard: `http://<node-ip>:30008/`

### Database Access
- H2 Console (Patient): `http://<node-ip>:30009/h2-console`
- H2 Console (Prescription): `http://<node-ip>:30010/h2-console`
- H2 Console (Reimbursement): `http://<node-ip>:30011/h2-console`

### Health Endpoints
```bash
# Check service health
kubectl get pods
kubectl get services
kubectl get ingress

# View logs
kubectl logs -f deployment/ms-patient-deployment
kubectl logs -f deployment/registry-deployment
```

## 🛡️ Circuit Breaker Configuration

The prescription service implements circuit breaker patterns:
- **Fallback Method**: Returns default patient data when patient service is unavailable
- **Cache Integration**: Reduces external service calls with Caffeine cache
- **Timeout Configuration**: Prevents hanging requests

## 📊 Scaling and Performance

### Horizontal Pod Autoscaler
- Monitors CPU utilization across pods
- Automatically scales between 2-10 replicas
- Scales up when CPU > 15%

### Caching Strategy
- **Cache Provider**: Caffeine
- **TTL**: 60 seconds
- **Max Size**: 100 entries
- **Cache Key**: Patient ID

## 🚨 Troubleshooting

### Common Issues

1. **Services not discovering each other**
   ```bash
   # Check Eureka registration
   kubectl port-forward svc/registry-srv 8888:8888
   # Access http://localhost:8888
   ```

2. **Database connection issues**
   ```bash
   # Verify secrets
   kubectl get secrets mysql-secret -o yaml
   # Check MySQL pod logs
   kubectl logs -f deployment/mysql-with-secret-depy
   ```

3. **Ingress not working**
   ```bash
   # Check ingress controller
   kubectl get pods -n ingress-nginx
   # Verify ingress rules
   kubectl describe ingress path-based-ingress
   ```

4. **Auto-scaling not working**
   ```bash
   # Check HPA status
   kubectl get hpa
   # Verify metrics server
   kubectl top pods
   ```

### Useful Commands

```bash
# Check all deployments
kubectl get deployments

# Scale manually
kubectl scale deployment ms-patient-deployment --replicas=3

# View service endpoints
kubectl get endpoints

# Check resource usage
kubectl top pods
kubectl top nodes

# Clean up
kubectl delete -f K8S_Deployment_files/
```

## 📝 Environment Variables

### Registry Service
- `EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE`: Eureka server URL

### Microservices
- `eureka.client.serviceUrl.defaultZone`: Registry location
- `spring.application.instance_id`: Unique instance identifier
- `eureka.instance.hostname`: Service hostname
- `SPRING_DATASOURCE_*`: Database connection details

## 🔐 Security

- **Secrets Management**: Sensitive data stored in Kubernetes secrets
- **Network Policies**: Implement network segmentation (recommended)
- **RBAC**: Configure appropriate role-based access control
- **Image Security**: Use specific image tags and scan for vulnerabilities

## 🚀 Future Enhancements

- **Distributed Tracing**: Add Zipkin/Jaeger for request tracing
- **Centralized Logging**: Implement ELK stack
- **API Gateway**: Add Spring Cloud Gateway
- **Configuration Management**: Use Spring Cloud Config Server
- **Message Queues**: Add RabbitMQ/Apache Kafka for async communication
- **Monitoring**: Implement Prometheus and Grafana

## 📄 License

This project is for educational purposes and demonstrates enterprise microservices patterns with Kubernetes orchestration.
