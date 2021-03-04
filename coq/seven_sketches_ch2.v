(*
Preorder (X, <=) is a set X equipped with binary relation <= on X such that:
a) reflexivity: x <= x
b) transitivity: if x <= y and y <= z then x <= z
*)

(* (Nat, ==) is a preorder *)

Theorem nat_eq_reflexivity: forall n : nat,
 n = n.
Proof.
  intro.
  reflexivity.
Qed.

Theorem nat_eq_transitivity: forall n m k : nat,
 n = m ->
 m = k ->
 n = k.
Proof.
  intros n m k Hnm Hmk.
  rewrite Hnm.
  rewrite Hmk.
  reflexivity.
Qed.

(* (Nat, <=) is a preorder *)

Theorem nat_le_reflexivity: forall n : nat,
 n <= n.
Admitted.

Theorem nat_le_transitivity: forall n m k : nat,
 n <= m ->
 m <= k ->
 n <= k.
Admitted.


(* (Nat, >=) is preorder *)

Theorem nat_ge_reflexivity: forall n : nat,
 n >= n.
Admitted.

Theorem nat_ge_transitivity: forall n m k : nat,
 n >= m ->
 m >= k ->
 n >= k.
Admitted.

(*
Semigroup (X, ⊗) is set X with binary operation ⊗ such that:
associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
*)

(* (Bool, and) is a semigroup *)

Theorem bool_and_associativity: forall a b c : bool,
 (andb (andb a b) c) = (andb a (andb b c)).
Proof.
  intros.
  destruct a.
  - simpl. reflexivity.
  - simpl. reflexivity.
Qed.

(* (Bool, or) is a semigroup *)

Theorem bool_or_associativity: forall a b c : bool,
 (orb (orb a b) c) = (orb a (orb b c)).
Proof.
  intros.
  destruct a.
  - simpl. reflexivity.
  - simpl. reflexivity.
Qed.

(* (Nat, +) is a semigroup *)

Theorem nat_plus_associativity: forall a b c : nat,
 (a + b) + c = a + (b + c).
Admitted.

(* (Nat, * ) is a semigroup *)

Theorem nat_mult_associativity: forall a b c : nat,
 (a * b) * c = a * (b * c).
Admitted.

(*
Monoid (X, *, I) is set X equipped with:
a) binary operation *
b) element e from X
such that:
1) associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
2) unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
*)

(* (Bool, and, true) is a monoid *)

Theorem bool_and_true_left_unitality: forall b : bool,
 (andb true b)  = b.
Proof.
 intro b.
 simpl.
 reflexivity.
Qed.

Theorem bool_and_true_right_unitality: forall b : bool,
 (andb b true) = b.
Proof.
 intro.
 destruct b.
 - simpl. reflexivity.
 - simpl. reflexivity.
Qed.

(* (Bool, or, false) is a monoid *)

Theorem bool_or_false_left_unitality: forall b : bool,
 (orb false b) = b.
Proof.
 intro b.
 simpl.
 reflexivity.
Qed.

Theorem bool_or_false_right_unitality: forall b : bool,
 (orb b false) = b.
Proof.
 intro.
 destruct b.
 - simpl. reflexivity.
 - simpl. reflexivity.
Qed.

(* (Nat, +, 0) is a monoid *)

Theorem nat_plus_0_left_unitality: forall n : nat,
 0 + n  = n.
Proof.
 intros n.
 simpl.
 reflexivity.
Qed.

Theorem nat_plus_0_right_unitality: forall n : nat,
 n + 0 = n.
Proof.
 intro n.
 induction n as [| n1 Hn1].
 - simpl. reflexivity.
 - simpl. rewrite Hn1. reflexivity.
Qed.


(* (Nat, *, 1) is a monoid *)

Theorem nat_mult_1_left_unitality: forall n : nat,
 1 * n  = n.
Proof.
 intros n.
 simpl.
Admitted.

Theorem nat_mult_1_right_unitality: forall n : nat,
 n * 1 = n.
Admitted.

(*
Symmetric Monoid (X, *, I) is set X equipped with:
a) binary operation *
b) element e from X
such that:
1) associativity: forall a,b,c ∈ X, (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
2) unitality: forall a ∈ X, I ⊗ a = a and a ⊗ I = a
3) symmetry: forall a,b ∈ X, a ⊗ b = b ⊗ a
*)

(* (Bool, and, true) is a symmetric monoidal *)

Theorem bool_and_true_symmetry: forall a b : bool,
 andb a b = andb b a.
Proof.
 intros a b.
 destruct b.
 - destruct a.
   + reflexivity.
   + reflexivity.
- destruct a.
   + reflexivity.
   + reflexivity.
Qed.

(* (Bool, or, false) is a symmetric monoidal *)

Theorem bool_or_false_symmetry: forall a b : bool,
 orb a b = orb b a.
Proof.
 intros a b.
 destruct b.
 - destruct a.
   + reflexivity.
   + reflexivity.
- destruct a.
   + reflexivity.
   + reflexivity.
Qed.

(* (Nat, +, 0) is a symmetric monoidal *)

Theorem nat_plus_symmetry: forall a b : nat,
 a + b = b + a.
Admitted.

(*
Preorder with monoidal structure (X, <=, ⊗, I) is a preorder (X, <=) equipped with:
- monoidal product: operation ⊗ : X × X -> X
- monoidal unit: element I
such that:
- monotonicity: if a1 <= b1 and a2 <= b2, then a1 ⊗ a2 <= b1 ⊗ b2
- unitality: I ⊗ a = a and a ⊗ I = a
- associativity: (a ⊗ b) ⊗ c = a ⊗ (b ⊗ c)
- symmetry: a ⊗ b = b ⊗ a

(Nat, <=, +, 0) natural numbers with <=, +, 0 form strict symmetric monoidal preorder:
0 is monoidal unit
+ is monoidal product
<= is preorder relation
*)

Theorem nat_le_plus_monotonicity: forall a b c d : nat,
 a <= b -> c <= d ->
 a + c <= b + d.
Admitted.


Theorem exercise_2_10_1_on_nat : forall t u v w x y z : nat,
 t <= v + w -> w + u <= x + z -> u + x <= y ->
 t + u <= y + z.
Proof.
 intros t u v w x y z H_t_vw H_wu_xz H_ux_y.
 Admitted.

