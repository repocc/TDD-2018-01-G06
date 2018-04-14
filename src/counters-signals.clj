(ns counters-signals (:require [parser :as parser]))

(defmulti initialize-counter-params (fn [params] (count params)))
(defmethod initialize-counter-params 0 [params] 0)
(defmethod initialize-counter-params 1 [params] 0)
(defmethod initialize-counter-params :default [params] {})

(defn initial-counter [rules] "Retorna un vector con el nombre del contador y el valor"
  (identity [(parser/get-counter-signal-name rules) (initialize-counter-params (parser/get-counter-params rules))])
)

(defn counter-rule-elements [rules] "Retorna un vector con el nombre del contador y un subvector con los parametros y condiciones"
  (identity [(parser/get-counter-signal-name rules) [(parser/get-counter-params rules) (parser/get-rule-condition rules)]])
)

(defn signal-rule-elements [rules] "Retorna un vector con el nombre de la señal y un subvector con la operacion y condicion"
  (identity [(parser/get-counter-signal-name rules) [(parser/get-signal-operation rules) (parser/get-rule-condition rules)]])
)

(defn initialize-counters [rules] "Se filtran solo los contadores y se obtiene el nombre del contador y su valor inicial, los cuales son almacenados en un diccionario"
  (into {} (map initial-counter (filter parser/is-a-counter rules)))
)

(defn initialize-counters-elements [rules] "Se filtran solo los contadores y se obtiene un diccionario con el nombre del contador y sus parametros y condiciones"
  (into {} (map counter-rule-elements (filter parser/is-a-counter rules)))
)

(defn initialize-signal-elements [rules] "Se filtran solo los contadores y se obtiene un diccionario con el nombre de la señal, su operacion y condicion"
  (into {} (map signal-rule-elements (filter parser/is-a-signal rules)))
)
