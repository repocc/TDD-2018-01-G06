(ns data-processor (:require [counter-signals :as counter-signals]))

;Retorna un vector de diccionarios, donde el primero tiene a los contadores y sus valores, el 2do los elementos de los contadores, el 3ro las señales y sus elementos y el 4to almacenara info para current/past
(defn initialize-processor [rules]
  [(counter-signals/initialize-counters rules) (counter-signals/initialize-counters-elements rules) (counter-signals/initialize-signal-elements rules) {}]
)

(defn get-counters-map [state] ;Retorna el primer diccionario de contadores y sus valores
  (first state)
)

(defmulti counter-status (fn [value counter-args] (type value))) ;Retorna el valor de los contadores
(defmethod counter-status clojure.lang.PersistentArrayMap [value counter-args] (get value counter-args))
(defmethod counter-status java.lang.Long [value counter-args] value)
(defmethod counter-status :default [value counter-args] 0)

(defn query-counter [state counter-name counter-args]
  (let [value (get (get-counters-map state) counter-name)] (counter-status value counter-args))
)

(def functions {"=" = "+" + "-" - "*" * "/" / "mod" mod "<" < ">" > "<=" <= ">=" >= "concat" str})

(defn get-function [function]
  ;Toma el operador pasado como parametro y llama a la funcion correspondiente
 (get functions function)
)

(defn apply-operator [function param1 param2]
  ;Aplica la funcion correspondiente del operator a los parametros
(apply (get-function function) [param1 param2])
)

