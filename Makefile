default:
	@echo "Please either make server or make client"

server:
	$(MAKE) -C CalculatorServer

client:
	$(MAKE) -C CalculatorClient