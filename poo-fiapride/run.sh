#!/bin/bash
# Script para compilar e rodar o FiapRide
# Execute: chmod +x run.sh && ./run.sh

echo "🔧 Compilando FiapRide..."
mkdir -p out

javac -d out \
  src/br/com/fiapride/model/Conduzivel.java \
  src/br/com/fiapride/model/Veiculo.java \
  src/br/com/fiapride/model/Passageiro.java \
  src/br/com/fiapride/model/Carro.java \
  src/br/com/fiapride/model/Moto.java \
  src/br/com/fiapride/model/Motorista.java \
  src/br/com/fiapride/model/Viagem.java \
  src/br/com/fiapride/main/FiapRideApp.java

if [ $? -eq 0 ]; then
  echo "✅ Compilação bem-sucedida!"
  echo ""
  echo "🚀 Executando FiapRide..."
  echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
  java -cp out br.com.fiapride.main.FiapRideApp
else
  echo "❌ Erro na compilação. Verifique os erros acima."
fi
