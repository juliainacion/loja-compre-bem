import { StatusBar } from 'expo-status-bar';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import TelaListaProdutos from './TelaListaProdutos';
import TelaDetalheProduto from './TelaDetalheProduto';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="ListaProdutos">
        <Stack.Screen
          name="ListaProdutos"
          component={TelaListaProdutos}
          options={{ title: 'Loja Compre Bem' }}
        />
        <Stack.Screen
          name="DetalheProduto"
          component={TelaDetalheProduto}
          options={{ title: 'Detalhe do Produto' }}
        />
      </Stack.Navigator>
      <StatusBar style="auto" />
    </NavigationContainer>
  );
}