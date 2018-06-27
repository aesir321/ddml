import pandas as pd

threemers = pd.read_csv('../output/uniref_90_3mers_4exs.csv')
print(threemers.sort_values('count', ascending=False).head())

fourmers = pd.read_csv('../output/uniref_90_4mers_4exs.csv')
print(fourmers.sort_values('count', ascending=False).head())

fivemers = pd.read_csv('../output/uniref_90_5mers_4exs.csv')
print(fivemers.sort_values('count', ascending=False).head())
