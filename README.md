### How to reproduce error

1. Run `Application` with `configuration.yml`
2. Wait for start of `Application`.
3. Run `ErrorTrigger`. You should see some number of 500 errors, but expected is 503.

#### The potential reason

Mapper `GenericJDBCExceptionMapper` is properly registered in Jersey, but exceptions from `UnitFromWorkAspect` 
are not wrapper with `MappableException` and are not mapped to the response from mapper.

The method `process` in the class `ServerRuntime$Responder` (line 476) cannot map not-mappable exception and default 500 is generated.  
