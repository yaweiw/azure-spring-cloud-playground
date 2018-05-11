1. Replace your-acr-repository with your acr name in pom.xml, and fill in your username and password. Please follow https://docs.microsoft.com/en-us/azure/aks/tutorial-kubernetes-prepare-acr if you don't have a acr.
2. Replace your-acr-repository with your acr name in kubernetes.yaml
3. Create AKS and setup kubectl by following https://docs.microsoft.com/en-us/azure/aks/ or 
  ```bash
  az login
  az account set -s subscriptionId
  az aks get-credentials -n aksName -g aksResourceGroup
  kubectl cluster-info
  ```
4. Run deploy.sh