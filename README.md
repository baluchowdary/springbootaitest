# springbootaitest
Springboot with Ollama phi3:mini model


S/W:

Ollama : 0.17.2

AI chat name : Ollama

AI chat model : phi3:mini

Springboot : 4.0.3

spring-ai-bom : 2.0.0-M2


get -> http://localhost:9090/ai/status

get --> http://localhost:9090/ai/stream?model=phi3:mini&prompt=java21 features

post --> http://localhost:9090/ai/generate

{
    "model": "phi3:mini",
    "prompt": "Write a Java method to sort a list."
}
