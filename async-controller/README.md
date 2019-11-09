# DeferredResult

DeferredResult 作为controller返回值时，处理request的线程在return 之后，会有一个新的线程异步处理，当setResult执行后，会通知容器生成response

